package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PrestigeJetViewModel(private val repository: PrestigeJetRepository) : ViewModel() {

    // 1. Aircraft StateFlow
    val aircraftList: StateFlow<List<Aircraft>> = repository.allAircraft
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 2. Luxury Concierge StateFlow
    val conciergeRequests: StateFlow<List<ConciergeRequest>> = repository.allConciergeRequests
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 3. Security Assessment StateFlow
    val securityAssessments: StateFlow<List<SecurityAssessment>> = repository.allSecurityAssessments
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 4. Crew Recruitment StateFlow
    val crewCandidates: StateFlow<List<CrewCandidate>> = repository.allCrewCandidates
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 6. Maintenance Logs StateFlow
    val maintenanceLogs: StateFlow<List<MaintenanceLog>> = repository.allMaintenanceLogs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 7. Fuel Quotes StateFlow
    val fuelQuotes: StateFlow<List<FuelQuote>> = repository.allFuelQuotes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 8. Charter Listings StateFlow
    val charterListings: StateFlow<List<CharterListing>> = repository.allCharterListings
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 9. Compliance Checks StateFlow
    val complianceChecks: StateFlow<List<ComplianceCheck>> = repository.allComplianceChecks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 10. Hangar Development StateFlow
    val hangarDevelopments: StateFlow<List<HangarDevelopment>> = repository.allHangarDevelopments
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 11. Carbon Offset Logs StateFlow
    val carbonOffsetLogs: StateFlow<List<CarbonOffsetLog>> = repository.allCarbonOffsetLogs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        prepopulateIfEmpty()
    }

    private fun prepopulateIfEmpty() {
        viewModelScope.launch {
            // Check if aircraft list is empty, if so, populate everything
            val currentAircraftList = repository.allAircraft.first()
            if (currentAircraftList.isEmpty()) {
                // 1. Core VIP Jets
                val jet1 = Aircraft(tailNumber = "N700XP", model = "Gulfstream G650ER", rangeNm = 7500, hoursFlown = 1240.5, yearOfManufacture = 2021, baseAirport = "KTEB (Teterboro)")
                val jet2 = Aircraft(tailNumber = "N990EL", model = "Bombardier Global 7500", rangeNm = 7700, hoursFlown = 650.0, yearOfManufacture = 2023, baseAirport = "KOPF (Opa-Locka Exec)")
                val jet3 = Aircraft(tailNumber = "N350AP", model = "Embraer Praetor 600", rangeNm = 4018, hoursFlown = 2100.8, yearOfManufacture = 2019, baseAirport = "LFPG (Paris Le Bourget)")
                
                repository.insertAircraft(jet1)
                repository.insertAircraft(jet2)
                repository.insertAircraft(jet3)

                // 2. Concierge Requests
                repository.insertConciergeRequest(ConciergeRequest(aircraftTail = "N700XP", requestType = "VIP Catering", description = "Beluga Caviar & vintage Dom Pérignon for 6 passengers, departing noon.", bookingDate = "2026-06-10", status = "Confirmed"))
                repository.insertConciergeRequest(ConciergeRequest(aircraftTail = "N990EL", requestType = "Chauffeur", description = "Mercedes Maybach S-Class on-tarmac pickup at LFPG.", bookingDate = "2026-06-15", status = "Pending"))
                repository.insertConciergeRequest(ConciergeRequest(aircraftTail = "N350AP", requestType = "Bespoke Stay", description = "Presidential Penthouse Suite booking at Villa d'Este, Lake Como.", bookingDate = "2026-06-20", status = "Confirmed"))

                // 3. Security Assessments
                repository.insertSecurityAssessment(SecurityAssessment(targetDestination = "SVMI - Caracas, Venezuela", requestDate = "2026-06-08", physicalGuardRequested = true, cyberAuditRequested = true, currentThreatLevel = "Moderate", responseDetails = "Armored SUV detail arranged. In-flight secure comms activated. Briefing documents compiled for crew.", status = "Evaluated"))
                repository.insertSecurityAssessment(SecurityAssessment(targetDestination = "HECA - Cairo, Egypt", requestDate = "2026-06-18", physicalGuardRequested = true, cyberAuditRequested = false, currentThreatLevel = "Low-Moderate", responseDetails = "FBO physical escort requested. Terminal safety protocols verified.", status = "Pending"))
                repository.insertSecurityAssessment(SecurityAssessment(targetDestination = "LFPG - Paris, France", requestDate = "2026-06-05", physicalGuardRequested = false, cyberAuditRequested = true, currentThreatLevel = "Low", responseDetails = "Cyber threat level nominal. Safe standard routing confirmed.", status = "Evaluated"))

                // 4. Elite Crew Candidates
                repository.insertCrewCandidate(CrewCandidate(fullName = "Capt. Sarah Jenkins", role = "Captain", experienceYears = 15, ratingsAndCerts = "G650 / Global 7500 Rating, FAA First Class Medical, 8,500 Hrs Flown", availabilityStatus = "Available", rating = 4.93, contactEmail = "jenkins.sarah@elitepilot.com"))
                repository.insertCrewCandidate(CrewCandidate(fullName = "Marcus Vance", role = "First Officer", experienceYears = 8, ratingsAndCerts = "Challenger 350 Rating, ATP Written, 3,200 Hrs Flown", availabilityStatus = "In-Interview", rating = 4.75, contactEmail = "m.vance@avcrew.com"))
                repository.insertCrewCandidate(CrewCandidate(fullName = "Chloe Laurent", role = "Cabin Attendant", experienceYears = 10, ratingsAndCerts = "VIP Wine Sommelier Certification, MedAire First Responder, Silver Service Accredited", availabilityStatus = "Available", rating = 4.98, contactEmail = "chloe.vip@sommeliercrew.com"))
                repository.insertCrewCandidate(CrewCandidate(fullName = "David Zhao", role = "Avionics Specialist", experienceYears = 12, ratingsAndCerts = "Honeywell Primus Epic Certified, FAA A&P License", availabilityStatus = "Available", rating = 4.88, contactEmail = "david.zhao@jetmaintenance.net"))

                // 6. Maintenance Logs
                repository.insertMaintenanceLog(MaintenanceLog(aircraftTail = "N700XP", taskName = "A-Check Routine Inspection", dueDate = "2026-06-20", maintenanceHub = "Savannah Gulfstream Service", estimatedCostUsd = 12500.00, status = "Scheduled"))
                repository.insertMaintenanceLog(MaintenanceLog(aircraftTail = "N350AP", taskName = "C-Check major airframe overhaul", dueDate = "2026-08-15", maintenanceHub = "Geneva RUAG Aerospace", estimatedCostUsd = 185000.00, status = "Outstanding"))
                repository.insertMaintenanceLog(MaintenanceLog(aircraftTail = "N990EL", taskName = "Honeywell Ka-Band Wi-Fi Upgrade", dueDate = "2026-06-05", maintenanceHub = "Dallas Bombardier Service Center", estimatedCostUsd = 75000.00, status = "In Progress"))

                // 7. Fuel Procurement Quotes
                repository.insertFuelQuote(FuelQuote(airportIcao = "KTEB", fboName = "Signature Flight Support", customPricePerGallon = 6.45, gallonsRequested = 2500, requestDate = "2026-06-05", status = "Confirmed"))
                repository.insertFuelQuote(FuelQuote(airportIcao = "LFPG", fboName = "Jet Aviation", customPricePerGallon = 6.12, gallonsRequested = 3000, requestDate = "2026-06-06", status = "Draft"))
                repository.insertFuelQuote(FuelQuote(airportIcao = "KPBI", fboName = "Atlantic Aviation PBI", customPricePerGallon = 5.85, gallonsRequested = 1800, requestDate = "2026-06-04", status = "Confirmed"))

                // 8. Charter Brokerage
                repository.insertCharterListing(CharterListing(aircraftTail = "N990EL", departureAirport = "KTEB (New York)", arrivalAirport = "KPBI (Palm Beach)", departureDate = "2026-06-12", ratePerHourUsd = 9500.00, isOpenForCharter = true, bookingsReceived = 2))
                repository.insertCharterListing(CharterListing(aircraftTail = "N350AP", departureAirport = "LSGG (Geneva)", arrivalAirport = "LFPB (Le Bourget)", departureDate = "2026-06-18", ratePerHourUsd = 6200.00, isOpenForCharter = true, bookingsReceived = 0))

                // 9. Regulatory Compliance
                repository.insertComplianceCheck(ComplianceCheck(requirementName = "RVSM Airworthiness Certificate", authority = "FAA", dueDate = "2026-11-20", complianceCategory = "Aircraft", status = "Compliant"))
                repository.insertComplianceCheck(ComplianceCheck(requirementName = "Pilot Class 1 Medical Renewal", authority = "FAA / EASA", dueDate = "2026-06-25", complianceCategory = "Pilot", status = "Warning"))
                repository.insertComplianceCheck(ComplianceCheck(requirementName = "Part 135 Annual Audit Validation", authority = "FAA", dueDate = "2026-07-04", complianceCategory = "Operation", status = "Outstanding"))

                // 10. Hangar Developments
                repository.insertHangarDevelopment(HangarDevelopment(airportHubCode = "KTEB (Teterboro)", hangarType = "Executive Custom Fit-out", sizeSqFt = 45000, buildingStage = "Interior Fit-out", progressPercent = 85, developmentAgentEmail = "estate@prestigehangars.com"))
                repository.insertHangarDevelopment(HangarDevelopment(airportHubCode = "KOPF (Miami)", hangarType = "State-of-the-art Multi-Jet Dome", sizeSqFt = 60000, buildingStage = "Groundbreaking", progressPercent = 15, developmentAgentEmail = "miami.build@prestigehangars.com"))

                // 11. Sustainability & Carbon Offset Logs
                repository.insertCarbonOffsetLog(CarbonOffsetLog(aircraftTail = "N700XP", flightHours = 6.2, sourceAirport = "KTEB", destinationAirport = "LFPG", tonsCo2Produced = 18.6, offsetProjectName = "Amazon Basin Canopy Preservation", offsetCostUsd = 558.00, offsetStatus = "Compensated"))
                repository.insertCarbonOffsetLog(CarbonOffsetLog(aircraftTail = "N350AP", flightHours = 3.5, sourceAirport = "LSGG", destinationAirport = "EGKB", tonsCo2Produced = 5.25, offsetProjectName = "SAF Blend Uplift Program (Geneva)", offsetCostUsd = 315.00, offsetStatus = "Outstanding"))
            }
        }
    }

    // 1. Aircraft methods
    fun addAircraft(tailNumber: String, model: String, rangeNm: Int, hoursFlown: Double, yearOfManufacture: Int, baseAirport: String) {
        viewModelScope.launch {
            repository.insertAircraft(Aircraft(tailNumber = tailNumber, model = model, rangeNm = rangeNm, hoursFlown = hoursFlown, yearOfManufacture = yearOfManufacture, baseAirport = baseAirport))
        }
    }
    fun deleteAircraft(aircraft: Aircraft) {
        viewModelScope.launch { repository.deleteAircraft(aircraft) }
    }

    // 2. Concierge Request methods
    fun addConciergeRequest(aircraftTail: String, requestType: String, description: String, bookingDate: String, status: String = "Pending") {
        viewModelScope.launch {
            repository.insertConciergeRequest(ConciergeRequest(aircraftTail = aircraftTail, requestType = requestType, description = description, bookingDate = bookingDate, status = status))
        }
    }
    fun deleteConciergeRequest(request: ConciergeRequest) {
        viewModelScope.launch { repository.deleteConciergeRequest(request) }
    }

    // 3. Security Assessment methods
    fun addSecurityAssessment(targetDestination: String, requestDate: String, physicalGuardRequested: Boolean, cyberAuditRequested: Boolean, threatLevel: String = "Moderate", responseDetails: String = "Evaluating routing parameters...") {
        viewModelScope.launch {
            repository.insertSecurityAssessment(SecurityAssessment(targetDestination = targetDestination, requestDate = requestDate, physicalGuardRequested = physicalGuardRequested, cyberAuditRequested = cyberAuditRequested, currentThreatLevel = threatLevel, responseDetails = responseDetails, status = "Pending"))
        }
    }
    fun deleteSecurityAssessment(assessment: SecurityAssessment) {
        viewModelScope.launch { repository.deleteSecurityAssessment(assessment) }
    }

    // 4. Crew Candidate methods
    fun addCrewCandidate(fullName: String, role: String, experienceYears: Int, ratingsAndCerts: String, availabilityStatus: String = "Available", rating: Double = 5.0, contactEmail: String) {
        viewModelScope.launch {
            repository.insertCrewCandidate(CrewCandidate(fullName = fullName, role = role, experienceYears = experienceYears, ratingsAndCerts = ratingsAndCerts, availabilityStatus = availabilityStatus, rating = rating, contactEmail = contactEmail))
        }
    }
    fun deleteCrewCandidate(candidate: CrewCandidate) {
        viewModelScope.launch { repository.deleteCrewCandidate(candidate) }
    }

    // 6. Maintenance Log methods
    fun addMaintenanceLog(aircraftTail: String, taskName: String, dueDate: String, maintenanceHub: String, estimatedCostUsd: Double, status: String = "Outstanding") {
        viewModelScope.launch {
            repository.insertMaintenanceLog(MaintenanceLog(aircraftTail = aircraftTail, taskName = taskName, dueDate = dueDate, maintenanceHub = maintenanceHub, estimatedCostUsd = estimatedCostUsd, status = status))
        }
    }
    fun deleteMaintenanceLog(log: MaintenanceLog) {
        viewModelScope.launch { repository.deleteMaintenanceLog(log) }
    }

    // 7. Fuel Quote methods
    fun addFuelQuote(airportIcao: String, fboName: String, pricePerGallon: Double, gallonsRequested: Int, requestDate: String, status: String = "Draft") {
        viewModelScope.launch {
            repository.insertFuelQuote(FuelQuote(airportIcao = airportIcao, fboName = fboName, customPricePerGallon = pricePerGallon, gallonsRequested = gallonsRequested, requestDate = requestDate, status = status))
        }
    }
    fun deleteFuelQuote(quote: FuelQuote) {
        viewModelScope.launch { repository.deleteFuelQuote(quote) }
    }

    // 8. Charter Listing methods
    fun addCharterListing(aircraftTail: String, departureAirport: String, arrivalAirport: String, departureDate: String, ratePerHourUsd: Double, isOpenForCharter: Boolean = true, bookingsReceived: Int = 0) {
        viewModelScope.launch {
            repository.insertCharterListing(CharterListing(aircraftTail = aircraftTail, departureAirport = departureAirport, arrivalAirport = arrivalAirport, departureDate = departureDate, ratePerHourUsd = ratePerHourUsd, isOpenForCharter = isOpenForCharter, bookingsReceived = bookingsReceived))
        }
    }
    fun deleteCharterListing(listing: CharterListing) {
        viewModelScope.launch { repository.deleteCharterListing(listing) }
    }

    // 9. Compliance Check methods
    fun addComplianceCheck(requirementName: String, authority: String, dueDate: String, complianceCategory: String, status: String = "Outstanding") {
        viewModelScope.launch {
            repository.insertComplianceCheck(ComplianceCheck(requirementName = requirementName, authority = authority, dueDate = dueDate, complianceCategory = complianceCategory, status = status))
        }
    }
    fun deleteComplianceCheck(check: ComplianceCheck) {
        viewModelScope.launch { repository.deleteComplianceCheck(check) }
    }

    // 10. Hangar Development methods
    fun addHangarDevelopment(airportHubCode: String, hangarType: String, sizeSqFt: Int, buildingStage: String = "Planning", progressPercent: Int = 0, developmentAgentEmail: String = "estate@prestigehangars.com") {
        viewModelScope.launch {
            repository.insertHangarDevelopment(HangarDevelopment(airportHubCode = airportHubCode, hangarType = hangarType, sizeSqFt = sizeSqFt, buildingStage = buildingStage, progressPercent = progressPercent, developmentAgentEmail = developmentAgentEmail))
        }
    }
    fun deleteHangarDevelopment(hangar: HangarDevelopment) {
        viewModelScope.launch { repository.deleteHangarDevelopment(hangar) }
    }

    // 11. Carbon Offset Log methods
    fun addCarbonOffsetLog(aircraftTail: String, flightHours: Double, sourceAirport: String, destinationAirport: String, tonsCo2: Double, project: String, cost: Double, status: String = "Outstanding") {
        viewModelScope.launch {
            repository.insertCarbonOffsetLog(CarbonOffsetLog(aircraftTail = aircraftTail, flightHours = flightHours, sourceAirport = sourceAirport, destinationAirport = destinationAirport, tonsCo2Produced = tonsCo2, offsetProjectName = project, offsetCostUsd = cost, offsetStatus = status))
        }
    }
    fun deleteCarbonOffsetLog(offsetLog: CarbonOffsetLog) {
        viewModelScope.launch { repository.deleteCarbonOffsetLog(offsetLog) }
    }
}

class ViewModelFactory(private val repository: PrestigeJetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrestigeJetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PrestigeJetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
