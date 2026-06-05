package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.*
import com.example.ui.theme.*
import java.io.Serializable

enum class ServiceType(val title: String, val subtitle: String, val icon: ImageVector, val tagValue: String) {
    AIRCRAFT("Aircraft Fleet", "Management & fleet specifications", Icons.Default.AirplanemodeActive, "aircraft_sec"),
    CONCIERGE("VIP Concierge", "VIP catering, chauffeur & luxury stays", Icons.Default.RoomService, "concierge_sec"),
    SECURITY("Security Detail", "Aviation physical security & cyber briefs", Icons.Default.Shield, "security_sec"),
    CREW("Crew Sourcing", "Recruit certified Captains & VIP attendants", Icons.Default.People, "crew_sec"),
    MAINTENANCE("Maintenance Hub", "Track routine logs and check countdowns", Icons.Default.Build, "maintenance_sec"),
    FUEL("Fuel Depot", "Compare global FBO quotes & order Jet-A", Icons.Default.LocalGasStation, "fuel_sec"),
    CHARTER("Charter Broker", "Monetize downtime empty legs with brokers", Icons.Default.MonetizationOn, "charter_sec"),
    COMPLIANCE("Compliance Desk", "FAA / EASA audits & captain credentials", Icons.Default.Verified, "compliance_sec"),
    HANGAR("Hangar Developer", "Track airport custom builds & layout plans", Icons.Default.Business, "hangar_sec"),
    SUSTAINABILITY("Carbon Advisory", "Offset flight carbon with certified projects", Icons.Default.Eco, "sustainability_sec")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestigeJetApp(viewModel: PrestigeJetViewModel) {
    var selectedService by remember { mutableStateOf<ServiceType?>(null) }

    // Collect all flows from room
    val aircraftList by viewModel.aircraftList.collectAsStateWithLifecycle()
    val conciergeList by viewModel.conciergeRequests.collectAsStateWithLifecycle()
    val securityList by viewModel.securityAssessments.collectAsStateWithLifecycle()
    val crewList by viewModel.crewCandidates.collectAsStateWithLifecycle()
    val maintenanceList by viewModel.maintenanceLogs.collectAsStateWithLifecycle()
    val fuelQuotes by viewModel.fuelQuotes.collectAsStateWithLifecycle()
    val charterList by viewModel.charterListings.collectAsStateWithLifecycle()
    val complianceList by viewModel.complianceChecks.collectAsStateWithLifecycle()
    val hangarList by viewModel.hangarDevelopments.collectAsStateWithLifecycle()
    val offsetLogs by viewModel.carbonOffsetLogs.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(SoftGold),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AirplanemodeActive,
                                contentDescription = "Aero Logo",
                                tint = ChampagneGold,
                                modifier = Modifier.size(20.dp).semantics { contentDescription = "Prestige Logo" }
                            )
                        }
                        Column {
                            Text(
                                text = "AVIATION HUB",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = ChampagneGold.copy(alpha = 0.7f),
                                letterSpacing = 1.2.sp
                            )
                            Text(
                                text = "PRESTIGE JET",
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp,
                                color = WarmSand,
                                fontSize = 18.sp
                            )
                        }
                    }
                },
                navigationIcon = {
                    if (selectedService != null) {
                        IconButton(
                            onClick = { selectedService = null },
                            modifier = Modifier.testTag("back_to_dashboard")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = WarmSand
                            )
                        }
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MidnightNavy)
                            .border(1.dp, ChampagneGold.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "CHAIRMAN CLUB",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = ChampagneGold,
                            letterSpacing = 1.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkNavy,
                    titleContentColor = WarmSand
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MidnightNavy,
                tonalElevation = 8.dp,
                modifier = Modifier.navigationBarsPadding().height(72.dp)
            ) {
                NavigationBarItem(
                    selected = selectedService == null,
                    onClick = { selectedService = null },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home", fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ChampagneGold,
                        selectedTextColor = WarmSand,
                        indicatorColor = SoftGold,
                        unselectedIconColor = WarmSand.copy(alpha = 0.45f),
                        unselectedTextColor = WarmSand.copy(alpha = 0.45f)
                    )
                )
                NavigationBarItem(
                    selected = selectedService == ServiceType.AIRCRAFT,
                    onClick = { selectedService = ServiceType.AIRCRAFT },
                    icon = { Icon(Icons.Default.AirplanemodeActive, contentDescription = "Fleet") },
                    label = { Text("Fleet", fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ChampagneGold,
                        selectedTextColor = WarmSand,
                        indicatorColor = SoftGold,
                        unselectedIconColor = WarmSand.copy(alpha = 0.45f),
                        unselectedTextColor = WarmSand.copy(alpha = 0.45f)
                    )
                )
                NavigationBarItem(
                    selected = selectedService == ServiceType.COMPLIANCE,
                    onClick = { selectedService = ServiceType.COMPLIANCE },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Alerts") },
                    label = { Text("Alerts", fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ChampagneGold,
                        selectedTextColor = WarmSand,
                        indicatorColor = SoftGold,
                        unselectedIconColor = WarmSand.copy(alpha = 0.45f),
                        unselectedTextColor = WarmSand.copy(alpha = 0.45f)
                    )
                )
                NavigationBarItem(
                    selected = selectedService == ServiceType.CONCIERGE,
                    onClick = { selectedService = ServiceType.CONCIERGE },
                    icon = { Icon(Icons.Default.Business, contentDescription = "Services") },
                    label = { Text("Services", fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ChampagneGold,
                        selectedTextColor = WarmSand,
                        indicatorColor = SoftGold,
                        unselectedIconColor = WarmSand.copy(alpha = 0.45f),
                        unselectedTextColor = WarmSand.copy(alpha = 0.45f)
                    )
                )
            }
        },
        containerColor = DarkNavy
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(DarkNavy, MidnightNavy)
                    )
                )
        ) {
            if (selectedService == null) {
                // Dashboard Welcome & Summary Stats
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        UserProfileHeader()
                    }

                    item {
                        ExecutiveQuickStats(
                            fleetCount = aircraftList.size,
                            carbonCompensated = offsetLogs.filter { it.offsetStatus == "Compensated" }.sumOf { it.tonsCo2Produced },
                            pendingConcierge = conciergeList.filter { it.status == "Pending" }.size,
                            complianceAlerts = complianceList.filter { it.status != "Compliant" }.size
                        )
                    }

                    item {
                        Text(
                            text = "Elite Fleet Commands",
                            color = ChampagneGold,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            letterSpacing = 1.sp,
                            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                        )
                    }

                    // Dashboard Grid of 10 Core Services
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(bottom = 24.dp)
                        ) {
                            val items = ServiceType.values()
                            // Chunking into rows of 2 for beautiful grid display
                            items.toList().chunked(2).forEach { rowItems ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    rowItems.forEach { service ->
                                        DashboardCard(
                                            service = service,
                                            modifier = Modifier.weight(1f),
                                            onClick = { selectedService = service }
                                        )
                                    }
                                    if (rowItems.size == 1) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                // Details view for selected service
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    when (selectedService) {
                        ServiceType.AIRCRAFT -> AircraftManagementScreen(aircraftList, viewModel)
                        ServiceType.CONCIERGE -> LuxuryConciergeScreen(conciergeList, aircraftList, viewModel)
                        ServiceType.SECURITY -> SecurityConsultingScreen(securityList, viewModel)
                        ServiceType.CREW -> CrewRecruitmentScreen(crewList, viewModel)
                        ServiceType.MAINTENANCE -> MaintenanceScreen(maintenanceList, aircraftList, viewModel)
                        ServiceType.FUEL -> FuelProcurementScreen(fuelQuotes, viewModel)
                        ServiceType.CHARTER -> CharterBrokerageScreen(charterList, aircraftList, viewModel)
                        ServiceType.COMPLIANCE -> RegulatoryComplianceScreen(complianceList, viewModel)
                        ServiceType.HANGAR -> HangarDevelopmentScreen(hangarList, viewModel)
                        ServiceType.SUSTAINABILITY -> SustainabilityScreen(offsetLogs, aircraftList, viewModel)
                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfileHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = SlateBlue),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(ChampagneGold),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "P",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = MidnightNavy,
                    fontFamily = FontFamily.Serif
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "primorose98@gmail.com",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = WarmSand,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Primary Private Jet Fleet Coordinator",
                    fontSize = 12.sp,
                    color = PlatinumSilver.copy(alpha = 0.7f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating Star",
                        tint = ChampagneGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Global Executive Tier",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ChampagneGold
                    )
                }
            }
        }
    }
}

@Composable
fun ExecutiveQuickStats(
    fleetCount: Int,
    carbonCompensated: Double,
    pendingConcierge: Int,
    complianceAlerts: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatPill(
            title = "Fleet size",
            value = "$fleetCount Jets",
            icon = Icons.Default.AirplanemodeActive,
            modifier = Modifier.weight(1f),
            highlight = false
        )
        StatPill(
            title = "CO2 Offset",
            value = "%.1f Tons".format(carbonCompensated),
            icon = Icons.Default.Eco,
            modifier = Modifier.weight(1f),
            highlight = true
        )
        StatPill(
            title = "VIP Tasks",
            value = "$pendingConcierge Pending",
            icon = Icons.Default.RoomService,
            modifier = Modifier.weight(1f),
            highlight = pendingConcierge > 0
        )
    }
}

@Composable
fun StatPill(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    highlight: Boolean = false
) {
    val containerBg = if (highlight) ChampagneGold else SlateBlue
    val contentColor = if (highlight) SoftGold else ChampagneGold
    val textColor = if (highlight) Color.White else WarmSand
    val subtextColor = if (highlight) SoftGold.copy(alpha = 0.85f) else PlatinumSilver.copy(alpha = 0.7f)
    val borderStroke = if (highlight) null else BorderStroke(1.dp, LightGrey)

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerBg),
        border = borderStroke,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = title.uppercase(),
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = subtextColor,
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
fun DashboardCard(
    service: ServiceType,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .testTag(service.tagValue)
            .height(115.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = SlateBlue),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.15f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(DarkNavy)
                    .border(1.dp, ChampagneGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = service.icon,
                    contentDescription = service.title,
                    tint = ChampagneGold,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column {
                Text(
                    text = service.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = WarmSand,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = service.subtitle,
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    color = PlatinumSilver.copy(alpha = 0.6f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


// ==========================================
// 1. Aircraft Management Screen
// ==========================================
@Composable
fun AircraftManagementScreen(aircraftList: List<Aircraft>, viewModel: PrestigeJetViewModel) {
    var showForm by remember { mutableStateOf(false) }

    var tail by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var range by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var base by remember { mutableStateOf("") }

    var feedbackMsg by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Aircraft Management",
                buttonText = if (showForm) "View Fleet" else "Register Jet",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.4f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Register Private Jet Platform", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        OutlinedTextField(
                            value = tail,
                            onValueChange = { tail = it },
                            label = { Text("Tail Registry Number (e.g. N700XP)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f), focusedLabelColor = ChampagneGold),
                            modifier = Modifier.fillMaxWidth().testTag("add_aircraft_tail")
                        )

                        OutlinedTextField(
                            value = model,
                            onValueChange = { model = it },
                            label = { Text("Aircraft Model (e.g. Gulfstream G650)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f), focusedLabelColor = ChampagneGold),
                            modifier = Modifier.fillMaxWidth().testTag("add_aircraft_model")
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = range,
                                onValueChange = { range = it },
                                label = { Text("Max Range (NM)", color = WarmSand) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1f)
                            )
                            OutlinedTextField(
                                value = hours,
                                onValueChange = { hours = it },
                                label = { Text("Total Hours Flown", color = WarmSand) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = year,
                                onValueChange = { year = it },
                                label = { Text("Year of Manufacture", color = WarmSand) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1f)
                            )
                            OutlinedTextField(
                                value = base,
                                onValueChange = { base = it },
                                label = { Text("Home Base (e.g. KTEB)", color = WarmSand) },
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Button(
                            onClick = {
                                if (tail.isNotBlank() && model.isNotBlank()) {
                                    viewModel.addAircraft(
                                        tailNumber = tail.uppercase(),
                                        model = model,
                                        rangeNm = range.toIntOrNull() ?: 5000,
                                        hoursFlown = hours.toDoubleOrNull() ?: 100.0,
                                        yearOfManufacture = year.toIntOrNull() ?: 2020,
                                        baseAirport = base.uppercase()
                                    )
                                    tail = ""; model = ""; range = ""; hours = ""; year = ""; base = ""
                                    feedbackMsg = "Aircraft Registry saved to hangar database."
                                    showForm = false
                                } else {
                                    feedbackMsg = "Please complete tail number and model fields."
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("save_aircraft_btn")
                        ) {
                            Text("SAVE TO REGISTER", fontWeight = FontWeight.Bold)
                        }

                        if (feedbackMsg.isNotBlank()) {
                            Text(feedbackMsg, color = ChampagneGold, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                }
            }
        } else {
            if (aircraftList.isEmpty()) {
                item { EmptyListPlaceholder("No Registered Fleet Jets Found") }
            } else {
                items(aircraftList) { jet ->
                    AircraftRowItem(jet = jet, onDelete = { viewModel.deleteAircraft(jet) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Aviation Management Insights",
                text = "Pre-flight airworthiness directives promote strict cockpit maintenance routines. Keep log entries updated daily to guarantee highest resale metrics."
            )
        }
    }
}

@Composable
fun AircraftRowItem(jet: Aircraft, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = jet.tailNumber, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = ChampagneGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(ChampagneGold.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "Built ${jet.yearOfManufacture}", fontSize = 10.sp, color = ChampagneGold, fontWeight = FontWeight.Bold)
                    }
                }
                Text(text = jet.model, fontWeight = FontWeight.Medium, fontSize = 14.sp, color = WarmSand, modifier = Modifier.padding(top = 2.dp))
                
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(text = "Range: ${jet.rangeNm} NM", fontSize = 12.sp, color = PlatinumSilver.copy(alpha = 0.7f))
                    Text(text = "Hours: ${jet.hoursFlown} hrs", fontSize = 12.sp, color = PlatinumSilver.copy(alpha = 0.7f))
                }
                Text(text = "Base Hub: ${jet.baseAirport}", fontSize = 12.sp, color = PlatinumSilver.copy(alpha = 0.7f), modifier = Modifier.padding(top = 2.dp))
            }
            IconButton(
                onClick = onDelete,
                modifier = Modifier.testTag("delete_aircraft_${jet.tailNumber}")
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Registry",
                    tint = Color.White.copy(alpha = 0.4f)
                )
            }
        }
    }
}


// ==========================================
// 2. Luxury Concierge Screen
// ==========================================
@Composable
fun LuxuryConciergeScreen(
    conciergeList: List<ConciergeRequest>,
    aircraftList: List<Aircraft>,
    viewModel: PrestigeJetViewModel
) {
    var showForm by remember { mutableStateOf(false) }

    var selectedAircraft by remember { mutableStateOf(aircraftList.firstOrNull()?.tailNumber ?: "N700XP") }
    var serviceType by remember { mutableStateOf("VIP Catering") }
    var details by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Luxury Concierge",
                buttonText = if (showForm) "Requests Logs" else "New Request",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Elite Concierge Booking", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        // Aircraft select
                        Text("Select Jet Registry", fontSize = 12.sp, color = PlatinumSilver)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val tails = if (aircraftList.isEmpty()) listOf("N700XP", "N990EL") else aircraftList.map { it.tailNumber }
                            tails.forEach { tailOption ->
                                val active = selectedAircraft == tailOption
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (active) ChampagneGold else DarkNavy)
                                        .clickable { selectedAircraft = tailOption }
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = tailOption,
                                        color = if (active) MidnightNavy else WarmSand,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }

                        // Category select
                        Text("Concierge Class", fontSize = 12.sp, color = PlatinumSilver)
                        val types = listOf("VIP Catering", "Chauffeur", "Bespoke Stay")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            types.forEach { t ->
                                val active = serviceType == t
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (active) ChampagneGold else DarkNavy)
                                        .clickable { serviceType = t }
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = t,
                                        color = if (active) MidnightNavy else WarmSand,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }

                        OutlinedTextField(
                            value = details,
                            onValueChange = { details = it },
                            label = { Text("Details (Menu items, hotel details, luggage needs)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = date,
                            onValueChange = { date = it },
                            label = { Text("Booking Date (YYYY-MM-DD)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                if (details.isNotBlank() && date.isNotBlank()) {
                                    viewModel.addConciergeRequest(
                                        aircraftTail = selectedAircraft,
                                        requestType = serviceType,
                                        description = details,
                                        bookingDate = date
                                    )
                                    details = ""; date = ""
                                    showForm = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier.fillMaxWidth().testTag("save_concierge_btn")
                        ) {
                            Text("DISPATCH REQUEST", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            if (conciergeList.isEmpty()) {
                item { EmptyListPlaceholder("No Concierge Assignments Drafted") }
            } else {
                items(conciergeList) { req ->
                    ConciergeRowItem(req = req, onDelete = { viewModel.deleteConciergeRequest(req) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Elite Dining & Transport",
                text = "Prestige partners specialize in airfield Michelin-star menus. Chauffeurs possess terminal security permits for immediate terminal airside tarmac access worldwide."
            )
        }
    }
}

@Composable
fun ConciergeRowItem(req: ConciergeRequest, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = req.requestType, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = ChampagneGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(ChampagneGold.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = req.aircraftTail, fontSize = 10.sp, color = ChampagneGold)
                    }
                }
                Text(text = req.description, fontSize = 13.sp, color = WarmSand, modifier = Modifier.padding(top = 4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Date: ${req.bookingDate}", fontSize = 11.sp, color = PlatinumSilver.copy(alpha = 0.6f))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (req.status == "Confirmed") Color(0xFF2E7D32) else Color(0xFFE65100))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = req.status.uppercase(), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}


// ==========================================
// 3. Aviation Security Screening
// ==========================================
@Composable
fun SecurityConsultingScreen(securityList: List<SecurityAssessment>, viewModel: PrestigeJetViewModel) {
    var showForm by remember { mutableStateOf(false) }

    var dest by remember { mutableStateOf("") }
    var physicalGuard by remember { mutableStateOf(false) }
    var cyberAudit by remember { mutableStateOf(false) }
    var requestDate by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Security Consulting",
                buttonText = if (showForm) "View Assessments" else "Request Briefing",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Risk Threat Brief Inquiry", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        OutlinedTextField(
                            value = dest,
                            onValueChange = { dest = it },
                            label = { Text("Target Airport ICAO or Destination City", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = requestDate,
                            onValueChange = { requestDate = it },
                            label = { Text("Arrival Date (YYYY-MM-DD)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().clickable { physicalGuard = !physicalGuard }
                        ) {
                            Checkbox(
                                checked = physicalGuard,
                                onCheckedChange = { physicalGuard = it },
                                colors = CheckboxDefaults.colors(checkedColor = ChampagneGold)
                            )
                            Text("Request On-Ground Executive Security Guard", color = WarmSand, fontSize = 13.sp)
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().clickable { cyberAudit = !cyberAudit }
                        ) {
                            Checkbox(
                                checked = cyberAudit,
                                onCheckedChange = { cyberAudit = it },
                                colors = CheckboxDefaults.colors(checkedColor = ChampagneGold)
                            )
                            Text("Secure SATCOM Cyber Assessment (Wi-Fi encryption)", color = WarmSand, fontSize = 13.sp)
                        }

                        Button(
                            onClick = {
                                if (dest.isNotBlank()) {
                                    viewModel.addSecurityAssessment(
                                        targetDestination = dest.uppercase(),
                                        requestDate = requestDate,
                                        physicalGuardRequested = physicalGuard,
                                        cyberAuditRequested = cyberAudit
                                    )
                                    dest = ""; requestDate = ""; physicalGuard = false; cyberAudit = false
                                    showForm = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier.fillMaxWidth().testTag("save_security_btn")
                        ) {
                            Text("SUBMIT FOR ASSESSMENT", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            if (securityList.isEmpty()) {
                item { EmptyListPlaceholder("No Threat Assessments Generated") }
            } else {
                items(securityList) { s ->
                    SecurityRowItem(assessment = s, onDelete = { viewModel.deleteSecurityAssessment(s) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Security & Cyber Advisory",
                text = "Pre-flight threat analysis helps flight crews adjust fuel plans and emergency escape flight levels. Secure high-frequency avionics links at all flight hubs."
            )
        }
    }
}

@Composable
fun SecurityRowItem(assessment: SecurityAssessment, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = assessment.targetDestination, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = ChampagneGold)
                Text(text = "Proposed Date: ${assessment.requestDate}", fontSize = 12.sp, color = PlatinumSilver.copy(alpha = 0.6f))
                
                Row(modifier = Modifier.padding(top = 4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (assessment.physicalGuardRequested) {
                        SecurityBadge("Armed Escort Requested")
                    }
                    if (assessment.cyberAuditRequested) {
                        SecurityBadge("Satcom Cyber Checked")
                    }
                }
                Text(
                    text = "Security Brief: ${assessment.responseDetails}",
                    fontSize = 12.sp,
                    color = WarmSand,
                    modifier = Modifier.padding(top = 6.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                when (assessment.currentThreatLevel) {
                                    "Severe" -> Color(0xFFC62828)
                                    "High" -> Color(0xFFE65100)
                                    "Moderate" -> Color(0xFFF9A825)
                                    else -> Color(0xFF2E7D32)
                                },
                                RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "${assessment.currentThreatLevel.uppercase()} RISK", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}

@Composable
fun SecurityBadge(label: String) {
    Box(
        modifier = Modifier
            .background(SlateBlue, RoundedCornerShape(4.dp))
            .border(1.dp, PlatinumSilver.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(text = label, fontSize = 9.sp, color = PlatinumSilver)
    }
}


// ==========================================
// 4. Crew Recruitment Agency Screen
// ==========================================
@Composable
fun CrewRecruitmentScreen(crewList: List<CrewCandidate>, viewModel: PrestigeJetViewModel) {
    var showForm by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("Captain") }
    var experience by remember { mutableStateOf("") }
    var certs by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Crew Recruitment",
                buttonText = if (showForm) "Available Talents" else "Post Open Application",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Aviation Crew Application Intake", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Full Name", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text("Select Primary Aviation Role", fontSize = 12.sp, color = PlatinumSilver)
                        val roles = listOf("Captain", "First Officer", "Cabin Attendant", "Avionics Specialist")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            roles.forEach { r ->
                                val active = role == r
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (active) ChampagneGold else DarkNavy)
                                        .clickable { role = r }
                                        .padding(horizontal = 8.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = r,
                                        color = if (active) MidnightNavy else WarmSand,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }

                        OutlinedTextField(
                            value = experience,
                            onValueChange = { experience = it },
                            label = { Text("Years of Aviation Experience", color = WarmSand) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = certs,
                            onValueChange = { certs = it },
                            label = { Text("Ratings & Certs (e.g. Gulfstream G650 rating)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Secure Contact Email", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                if (name.isNotBlank() && email.isNotBlank()) {
                                    viewModel.addCrewCandidate(
                                        fullName = name,
                                        role = role,
                                        experienceYears = experience.toIntOrNull() ?: 5,
                                        ratingsAndCerts = certs,
                                        contactEmail = email
                                    )
                                    name = ""; experience = ""; certs = ""; email = ""
                                    showForm = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier.fillMaxWidth().testTag("save_crew_btn")
                        ) {
                            Text("POST PROFILE TO DATABASE", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            if (crewList.isEmpty()) {
                item { EmptyListPlaceholder("No Crew Profiles in Recruitment Database") }
            } else {
                items(crewList) { candidate ->
                    CrewCandidateRowItem(candidate = candidate, onDelete = { viewModel.deleteCrewCandidate(candidate) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Crew Screening Standards",
                text = "We source crew with extensive heavy-jet operations history, luxury yacht silver services backgrounds, and zero incident reports."
            )
        }
    }
}

@Composable
fun CrewCandidateRowItem(candidate: CrewCandidate, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = candidate.fullName, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = WarmSand)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(ChampagneGold.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "Rating: ${candidate.rating} ⭐", fontSize = 10.sp, color = ChampagneGold)
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "${candidate.role} • ${candidate.experienceYears} Yrs Experience", fontSize = 13.sp, color = ChampagneGold, fontWeight = FontWeight.SemiBold)
                Text(text = "Credentials: ${candidate.ratingsAndCerts}", fontSize = 12.sp, color = PlatinumSilver.copy(alpha = 0.7f), modifier = Modifier.padding(top = 4.dp))
                Text(text = "Secure Email: ${candidate.contactEmail}", fontSize = 11.sp, color = PlatinumSilver.copy(alpha = 0.5f), modifier = Modifier.padding(top = 2.dp))
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}


// ==========================================
// 6. Maintenance Screen
// ==========================================
@Composable
fun MaintenanceScreen(
    maintenanceList: List<MaintenanceLog>,
    aircraftList: List<Aircraft>,
    viewModel: PrestigeJetViewModel
) {
    var showForm by remember { mutableStateOf(false) }

    var selectedAircraft by remember { mutableStateOf(aircraftList.firstOrNull()?.tailNumber ?: "N700XP") }
    var taskName by remember { mutableStateOf("C-Check Airframe Inspection") }
    var dueDate by remember { mutableStateOf("") }
    var hub by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Maintenance Hub",
                buttonText = if (showForm) "Schedule Logs" else "Schedule Service",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Log Maintenance Overhaul", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        Text("Select Jet Registry", fontSize = 12.sp, color = PlatinumSilver)
                        val tails = if (aircraftList.isEmpty()) listOf("N700XP", "N990EL") else aircraftList.map { it.tailNumber }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            tails.forEach { tail ->
                                val active = selectedAircraft == tail
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (active) ChampagneGold else DarkNavy)
                                        .clickable { selectedAircraft = tail }
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(text = tail, color = if (active) MidnightNavy else WarmSand, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }
                        }

                        OutlinedTextField(
                            value = taskName,
                            onValueChange = { taskName = it },
                            label = { Text("Task (e.g. Avionics upgrade / C-Check)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = dueDate,
                            onValueChange = { dueDate = it },
                            label = { Text("Due Date (YYYY-MM-DD)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = hub,
                            onValueChange = { hub = it },
                            label = { Text("Authorized Service Center Hub", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = cost,
                            onValueChange = { cost = it },
                            label = { Text("Estimated Cost (USD)", color = WarmSand) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                if (dueDate.isNotBlank() && hub.isNotBlank()) {
                                    viewModel.addMaintenanceLog(
                                        aircraftTail = selectedAircraft,
                                        taskName = taskName,
                                        dueDate = dueDate,
                                        maintenanceHub = hub,
                                        estimatedCostUsd = cost.toDoubleOrNull() ?: 10000.0
                                    )
                                    dueDate = ""; hub = ""; cost = ""
                                    showForm = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier.fillMaxWidth().testTag("save_maintenance_btn")
                        ) {
                            Text("SAVE MAINTENANCE RECORD", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            if (maintenanceList.isEmpty()) {
                item { EmptyListPlaceholder("No Maintenance Operations Outstanding") }
            } else {
                items(maintenanceList) { log ->
                    MaintenanceRowItem(log = log, onDelete = { viewModel.deleteMaintenanceLog(log) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Authorized MRO Centers",
                text = "Gulfstream Savannah and Bombardier Dallas maintain 24/7 dedicated parts hotlines. Regular structural and composite tests guarantee maximum safety factor scores."
            )
        }
    }
}

@Composable
fun MaintenanceRowItem(log: MaintenanceLog, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = log.aircraftTail, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = ChampagneGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(ChampagneGold.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "Est. \$${"%,.2f".format(log.estimatedCostUsd)}", fontSize = 10.sp, color = ChampagneGold)
                    }
                }
                Text(text = log.taskName, fontWeight = FontWeight.Medium, fontSize = 13.sp, color = WarmSand, modifier = Modifier.padding(top = 2.dp))
                Text(text = "Hub: ${log.maintenanceHub}", fontSize = 12.sp, color = PlatinumSilver.copy(alpha = 0.7f), modifier = Modifier.padding(top = 2.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Due Date: ${log.dueDate}", fontSize = 11.sp, color = PlatinumSilver.copy(alpha = 0.6f))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                when (log.status) {
                                    "Completed" -> Color(0xFF2E7D32)
                                    "In Progress" -> Color(0xFF1565C0)
                                    "Scheduled" -> Color(0xFFF57F17)
                                    else -> Color(0xFFC62828)
                                }
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = log.status.uppercase(), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}


// ==========================================
// 7. Fuel Procurement Services Screen
// ==========================================
@Composable
fun FuelProcurementScreen(fuelQuotes: List<FuelQuote>, viewModel: PrestigeJetViewModel) {
    var showForm by remember { mutableStateOf(false) }

    var icao by remember { mutableStateOf("") }
    var fbo by remember { mutableStateOf("") }
    var priceGauge by remember { mutableStateOf("6.15") }
    var gallons by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Fuel Procurement",
                buttonText = if (showForm) "View Jet-A Quotes" else "Request Custom Quote",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Draft High-Volume Fuel Request", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        OutlinedTextField(
                            value = icao,
                            onValueChange = { icao = it },
                            label = { Text("Airport ICAO Identifier (e.g. KLAX, EGKK)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = fbo,
                            onValueChange = { fbo = it },
                            label = { Text("Target FBO Agent (e.g. Signature Flight)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = priceGauge,
                                onValueChange = { priceGauge = it },
                                label = { Text("Fuel Price / Gal (USD)", color = WarmSand) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1f)
                            )
                            OutlinedTextField(
                                value = gallons,
                                onValueChange = { gallons = it },
                                label = { Text("Volume (Gallons)", color = WarmSand) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Button(
                            onClick = {
                                if (icao.isNotBlank() && gallons.isNotBlank()) {
                                    viewModel.addFuelQuote(
                                        airportIcao = icao.uppercase(),
                                        fboName = fbo,
                                        pricePerGallon = priceGauge.toDoubleOrNull() ?: 6.25,
                                        gallonsRequested = gallons.toIntOrNull() ?: 1000,
                                        requestDate = "2026-06-05" // current date mock
                                    )
                                    icao = ""; fbo = ""; gallons = ""
                                    showForm = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier.fillMaxWidth().testTag("save_fuel_btn")
                        ) {
                            Text("FINALIZE FUELING QUOTE", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            if (fuelQuotes.isEmpty()) {
                item { EmptyListPlaceholder("No Fuel Orders Recorded") }
            } else {
                items(fuelQuotes) { q ->
                    FuelRowItem(quote = q, onDelete = { viewModel.deleteFuelQuote(q) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Jet-A Contracting Guidelines",
                text = "Pre-negotiated contract fuel rates provide up to 25% savings over published FBO retail tariffs. Complete orders 24 hours prior to crew arrival to ensure prompt ramp uplift."
            )
        }
    }
}

@Composable
fun FuelRowItem(quote: FuelQuote, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = quote.airportIcao, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = ChampagneGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(ChampagneGold.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "\$${"%.2f".format(quote.customPricePerGallon)}/gal", fontSize = 10.sp, color = ChampagneGold)
                    }
                }
                Text(text = "FBO: ${quote.fboName}", fontSize = 13.sp, color = WarmSand, modifier = Modifier.padding(top = 2.dp))
                Text(text = "Volume Request: ${quote.gallonsRequested} Gallons", fontSize = 12.sp, color = PlatinumSilver, modifier = Modifier.padding(top = 4.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Contract Cost: \$${"%,.2f".format(quote.customPricePerGallon * quote.gallonsRequested)}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ChampagneGold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (quote.status == "Confirmed") Color(0xFF2E7D32) else Color(0xFFE5A020))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = quote.status.uppercase(), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}


// ==========================================
// 8. Charter Brokerage Screen
// ==========================================
@Composable
fun CharterBrokerageScreen(
    charterList: List<CharterListing>,
    aircraftList: List<Aircraft>,
    viewModel: PrestigeJetViewModel
) {
    var showForm by remember { mutableStateOf(false) }

    var selectedAircraft by remember { mutableStateOf(aircraftList.firstOrNull()?.tailNumber ?: "N700XP") }
    var departure by remember { mutableStateOf("") }
    var arrival by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Charter Brokerage",
                buttonText = if (showForm) "Downtime Listings" else "Publish Downtime",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("List Empty Leg Option", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        Text("Select Available Jet", fontSize = 12.sp, color = PlatinumSilver)
                        val tails = if (aircraftList.isEmpty()) listOf("N700XP", "N990EL") else aircraftList.map { it.tailNumber }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            tails.forEach { tail ->
                                val active = selectedAircraft == tail
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (active) ChampagneGold else DarkNavy)
                                        .clickable { selectedAircraft = tail }
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(text = tail, color = if (active) MidnightNavy else WarmSand, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }
                        }

                        OutlinedTextField(
                            value = departure,
                            onValueChange = { departure = it },
                            label = { Text("Departure (Origin Airport)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = arrival,
                            onValueChange = { arrival = it },
                            label = { Text("Destination Airport", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = date,
                                onValueChange = { date = it },
                                label = { Text("Date (YYYY-MM-DD)", color = WarmSand) },
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1.2f)
                            )
                            OutlinedTextField(
                                value = rate,
                                onValueChange = { rate = it },
                                label = { Text("Rate / Hr (USD)", color = WarmSand) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Button(
                            onClick = {
                                if (departure.isNotBlank() && arrival.isNotBlank() && rate.isNotBlank()) {
                                    viewModel.addCharterListing(
                                        aircraftTail = selectedAircraft,
                                        departureAirport = departure.uppercase(),
                                        arrivalAirport = arrival.uppercase(),
                                        departureDate = date,
                                        ratePerHourUsd = rate.toDoubleOrNull() ?: 8000.0
                                    )
                                    departure = ""; arrival = ""; date = ""; rate = ""
                                    showForm = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier.fillMaxWidth().testTag("add_charter_btn")
                        ) {
                            Text("SAVE CHARTER TO MARKETPLACE", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            if (charterList.isEmpty()) {
                item { EmptyListPlaceholder("No Downtime Listings Published") }
            } else {
                items(charterList) { c ->
                    CharterRowItem(listing = c, onDelete = { viewModel.deleteCharterListing(c) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Monetizing Downtime Assets",
                text = "Empty leg flights reduce asset overhead costs by up to 35%. Listing on the shared broker index connects dispatch systems with 1,200 prestige travel agencies."
            )
        }
    }
}

@Composable
fun CharterRowItem(listing: CharterListing, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${listing.departureAirport} ➔ ${listing.arrivalAirport}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = ChampagneGold
                    )
                }
                Text(text = "Aircraft Tail: ${listing.aircraftTail}", fontSize = 12.sp, color = WarmSand, modifier = Modifier.padding(top = 2.dp))
                Text(text = "Proposed Date: ${listing.departureDate}", fontSize = 12.sp, color = PlatinumSilver, modifier = Modifier.padding(top = 2.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Target Rate: \$${"%,.0f".format(listing.ratePerHourUsd)} / Hr",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = ChampagneGold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF1565C0))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "${listing.bookingsReceived} BOOKING INQUIRIES", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}


// ==========================================
// 9. Regulatory Compliance Screen
// ==========================================
@Composable
fun RegulatoryComplianceScreen(complianceList: List<ComplianceCheck>, viewModel: PrestigeJetViewModel) {
    var showForm by remember { mutableStateOf(false) }

    var checkName by remember { mutableStateOf("") }
    var auth by remember { mutableStateOf("FAA") }
    var date by remember { mutableStateOf("") }
    var valCategory by remember { mutableStateOf("Pilot") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Compliance Control",
                buttonText = if (showForm) "Audit Checklist" else "Add Mandate",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Add Critical Safety Mandate", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        OutlinedTextField(
                            value = checkName,
                            onValueChange = { checkName = it },
                            label = { Text("Regulatory Requirement / Inspection Name", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = auth,
                            onValueChange = { auth = it },
                            label = { Text("Aviation Agency Authority (e.g. FAA, EASA)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = date,
                            onValueChange = { date = it },
                            label = { Text("Inspection / Renewal Due Date", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text("Mandate Class", fontSize = 12.sp, color = PlatinumSilver)
                        val cats = listOf("Pilot", "Aircraft", "Operation")
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            cats.forEach { c ->
                                val active = valCategory == c
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (active) ChampagneGold else DarkNavy)
                                        .clickable { valCategory = c }
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(text = c, color = if (active) MidnightNavy else WarmSand, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }
                        }

                        Button(
                            onClick = {
                                if (checkName.isNotBlank() && date.isNotBlank()) {
                                    viewModel.addComplianceCheck(
                                        requirementName = checkName,
                                        authority = auth.uppercase(),
                                        dueDate = date,
                                        complianceCategory = valCategory
                                    )
                                    checkName = ""; date = ""
                                    showForm = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier.fillMaxWidth().testTag("save_compliance_btn")
                        ) {
                            Text("SAVE COMPLIANCE MANDATE", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            if (complianceList.isEmpty()) {
                item { EmptyListPlaceholder("No Registered Safety Audits Recorded") }
            } else {
                items(complianceList) { c ->
                    ComplianceRowItem(check = c, onDelete = { viewModel.deleteComplianceCheck(c) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Regulatory Compliance Consulting Services",
                text = "Keep pilots' Class 1 Medical validations and airworthiness paperwork synced. Missing international certificates can delay custom flight plans at foreign borders."
            )
        }
    }
}

@Composable
fun ComplianceRowItem(check: ComplianceCheck, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = check.requirementName, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = WarmSand)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Authority: ${check.authority} • Category: ${check.complianceCategory}", fontSize = 12.sp, color = ChampagneGold)
                
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Renew Before: ${check.dueDate}", fontSize = 11.sp, color = PlatinumSilver.copy(alpha = 0.7f))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                when (check.status) {
                                    "Compliant" -> Color(0xFF2E7D32)
                                    "Warning" -> Color(0xFFEF6C00)
                                    else -> Color(0xFFC62828)
                                }
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = check.status.uppercase(), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}


// ==========================================
// 10. Private Hangar Development Screen
// ==========================================
@Composable
fun HangarDevelopmentScreen(hangarList: List<HangarDevelopment>, viewModel: PrestigeJetViewModel) {
    var showForm by remember { mutableStateOf(false) }

    var airport by remember { mutableStateOf("") }
    var hangarType by remember { mutableStateOf("Executive Custom lounge hangar") }
    var sqFt by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Hangar Developer",
                buttonText = if (showForm) "Strategic Hubs" else "Request New Build",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Draft Hangar Development Plan", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        OutlinedTextField(
                            value = airport,
                            onValueChange = { airport = it },
                            label = { Text("Airport Hub Identifier (e.g. KSDL, KTEB)", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = hangarType,
                            onValueChange = { hangarType = it },
                            label = { Text("Hangar Specifications", color = WarmSand) },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = sqFt,
                            onValueChange = { sqFt = it },
                            label = { Text("Total Area size (Sq Ft)", color = WarmSand) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                if (airport.isNotBlank() && sqFt.isNotBlank()) {
                                    viewModel.addHangarDevelopment(
                                        airportHubCode = airport.uppercase(),
                                        hangarType = hangarType,
                                        sizeSqFt = sqFt.toIntOrNull() ?: 30000,
                                        buildingStage = "Planning",
                                        progressPercent = 5,
                                        developmentAgentEmail = "developer@prestigehangars.com"
                                    )
                                    airport = ""; sqFt = ""
                                    showForm = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier.fillMaxWidth().testTag("add_hangar_btn")
                        ) {
                            Text("COMMENCE STRATEGIC INQUIRY", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            if (hangarList.isEmpty()) {
                item { EmptyListPlaceholder("No Hangar Developments Filed") }
            } else {
                items(hangarList) { h ->
                    HangarRowItem(hangar = h, onDelete = { viewModel.deleteHangarDevelopment(h) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Private Aviation Hangar Real Estate",
                text = "Developing private airfields with modern climate containment floorboards shields sensitive radar, navigation, and avionics instruments from local weather fluctuations."
            )
        }
    }
}

@Composable
fun HangarRowItem(hangar: HangarDevelopment, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hub Location: ${hangar.airportHubCode}", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = ChampagneGold)
                Text(text = "Specs: ${hangar.hangarType}", fontSize = 13.sp, color = WarmSand)
                Text(text = "Size: ${hangar.sizeSqFt} Sq Ft", fontSize = 12.sp, color = PlatinumSilver, modifier = Modifier.padding(top = 2.dp))
                
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LinearProgressIndicator(
                        progress = { hangar.progressPercent / 100f },
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = ChampagneGold,
                        trackColor = DarkNavy
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "${hangar.progressPercent}%", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ChampagneGold)
                }
                
                Row(modifier = Modifier.padding(top = 6.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Stage: ${hangar.buildingStage}", fontSize = 11.sp, color = PlatinumSilver.copy(alpha = 0.6f))
                    Text(text = "Inquire: ${hangar.developmentAgentEmail}", fontSize = 10.sp, color = PlatinumSilver.copy(alpha = 0.5f), fontWeight = FontWeight.Medium)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}


// ==========================================
// 11. Sustainability & Carbon-Offset Screen
// ==========================================
@Composable
fun SustainabilityScreen(
    offsetLogs: List<CarbonOffsetLog>,
    aircraftList: List<Aircraft>,
    viewModel: PrestigeJetViewModel
) {
    var showForm by remember { mutableStateOf(false) }

    var selectedAircraft by remember { mutableStateOf(aircraftList.firstOrNull()?.tailNumber ?: "N700XP") }
    var hours by remember { mutableStateOf("") }
    var origin by remember { mutableStateOf("") }
    var dest by remember { mutableStateOf("") }
    var selectedProject by remember { mutableStateOf("Amazon Basin Canopy Preservation") }

    val tonsCo2 = (hours.toDoubleOrNull() ?: 0.0) * 3.0 // roughly 3.0 tons of CO2 per jet fuel hour
    val offsetCost = tonsCo2 * 30.0 // say, $30 per ton

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenTitleRow(
                title = "Carbon Advisory",
                buttonText = if (showForm) "Offset Log" else "Offset Flight",
                onButtonClick = { showForm = !showForm }
            )
        }

        if (showForm) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SlateBlue),
                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Compensate Jet Footprint", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 15.sp)

                        Text("Select Jet", fontSize = 12.sp, color = PlatinumSilver)
                        val tails = if (aircraftList.isEmpty()) listOf("N700XP", "N990EL") else aircraftList.map { it.tailNumber }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            tails.forEach { t ->
                                val active = selectedAircraft == t
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (active) ChampagneGold else DarkNavy)
                                        .clickable { selectedAircraft = t }
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(text = t, color = if (active) MidnightNavy else WarmSand, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }
                        }

                        OutlinedTextField(
                            value = hours,
                            onValueChange = { hours = it },
                            label = { Text("Flight Duration (Hours)", color = WarmSand) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = origin,
                                onValueChange = { origin = it },
                                label = { Text("Origin ICAO (e.g. EGKK)", color = WarmSand) },
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1f)
                            )
                            OutlinedTextField(
                                value = dest,
                                onValueChange = { dest = it },
                                label = { Text("Dest ICAO (e.g. LSGG)", color = WarmSand) },
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ChampagneGold, unfocusedBorderColor = PlatinumSilver.copy(alpha = 0.3f)),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Text("Certified Compensation Project", fontSize = 12.sp, color = PlatinumSilver)
                        val projects = listOf("Amazon Basin Canopy Preservation", "Certified Wind Power", "Sustainable Aviation Fuel Uplift")
                        projects.forEach { prj ->
                            val active = selectedProject == prj
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (active) ChampagneGold.copy(alpha = 0.2f) else Color.Transparent)
                                    .border(1.dp, if (active) ChampagneGold else PlatinumSilver.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                                    .clickable { selectedProject = prj }
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = active,
                                    onClick = { selectedProject = prj },
                                    colors = RadioButtonDefaults.colors(selectedColor = ChampagneGold, unselectedColor = WarmSand)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = prj, fontSize = 11.sp, color = WarmSand, fontWeight = FontWeight.SemiBold)
                            }
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = DarkNavy)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Carbon Analysis Estimate", fontWeight = FontWeight.Bold, color = ChampagneGold, fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Estimated Fuel CO2: %.2f Metric Tons".format(tonsCo2), color = WarmSand, fontSize = 12.sp)
                                Text("Certified Offset Investment: \$${"%,.2f".format(offsetCost)} USD", color = ChampagneGold, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }
                        }

                        Button(
                            onClick = {
                                if (hours.toDoubleOrNull() != null && origin.isNotBlank() && dest.isNotBlank()) {
                                    viewModel.addCarbonOffsetLog(
                                        aircraftTail = selectedAircraft,
                                        flightHours = hours.toDoubleOrNull() ?: 1.0,
                                        sourceAirport = origin.uppercase(),
                                        destinationAirport = dest.uppercase(),
                                        tonsCo2 = tonsCo2,
                                        project = selectedProject,
                                        cost = offsetCost,
                                        status = "Compensated"
                                    )
                                    hours = ""; origin = ""; dest = ""
                                    showForm = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ChampagneGold, contentColor = MidnightNavy),
                            modifier = Modifier.fillMaxWidth().testTag("add_offset_btn")
                        ) {
                            Text("INVEST & OFFSET CO2", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            if (offsetLogs.isEmpty()) {
                item { EmptyListPlaceholder("No Offset Flights Contributed") }
            } else {
                items(offsetLogs) { log ->
                    OffsetLogRowItem(log = log, onDelete = { viewModel.deleteCarbonOffsetLog(log) })
                }
            }
        }

        item {
            ExecutiveInsightSection(
                title = "Sustainability & SAF Uplift Advisory",
                text = "Voluntary carbon offsetting in private aviation shows dedication to net-zero frameworks. Sourcing Sustainable Aviation Fuel (SAF) reduces emissions by up to 80%."
            )
        }
    }
}

@Composable
fun OffsetLogRowItem(log: CarbonOffsetLog, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.12f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = log.aircraftTail, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = ChampagneGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF2E7D32).copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                            .border(1.dp, Color(0xFF2E7D32), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "%.1f Tons CO2".format(log.tonsCo2Produced), fontSize = 10.sp, color = Color(0xFF81C784), fontWeight = FontWeight.Bold)
                    }
                }
                Text(text = "${log.sourceAirport} ➔ ${log.destinationAirport} (${log.flightHours} hrs)", fontSize = 13.sp, color = WarmSand, modifier = Modifier.padding(top = 4.dp))
                Text(text = "Project: ${log.offsetProjectName}", fontSize = 12.sp, color = PlatinumSilver.copy(alpha = 0.7f), modifier = Modifier.padding(top = 2.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Offset Investment: \$${"%,.2f".format(log.offsetCostUsd)}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ChampagneGold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF2E7D32))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = log.offsetStatus.uppercase(), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}


// ==========================================
// Generic UI Helpers
// ==========================================

@Composable
fun ScreenTitleRow(
    title: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 18.sp,
            color = ChampagneGold
        )
        Button(
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors(containerColor = SlateBlue, contentColor = ChampagneGold),
            border = BorderStroke(1.dp, ChampagneGold)
        ) {
            Text(text = buttonText, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun EmptyListPlaceholder(message: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.3f)),
        border = BorderStroke(1.dp, PlatinumSilver.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Default.AirplanemodeInactive, contentDescription = "Empty", tint = PlatinumSilver.copy(alpha = 0.3f), modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = message, color = PlatinumSilver.copy(alpha = 0.6f), fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun ExecutiveInsightSection(title: String, text: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = SlateBlue.copy(alpha = 0.2f)),
        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.15f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Info", tint = ChampagneGold, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = ChampagneGold, letterSpacing = 0.75.sp)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = text,
                fontSize = 11.sp,
                lineHeight = 14.sp,
                color = PlatinumSilver.copy(alpha = 0.7f)
            )
        }
    }
}
