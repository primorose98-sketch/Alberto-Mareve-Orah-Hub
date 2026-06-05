package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// 1. Aircraft Management
@Entity(tableName = "aircraft")
data class Aircraft(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val tailNumber: String,
    val model: String,
    val rangeNm: Int,
    val hoursFlown: Double,
    val yearOfManufacture: Int,
    val baseAirport: String
) : Serializable

// 2. Luxury Concierge
@Entity(tableName = "concierge_requests")
data class ConciergeRequest(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val aircraftTail: String,
    val requestType: String, // VIP Catering, Chauffeur, Bespoke Stay
    val description: String,
    val bookingDate: String,
    val status: String // Pending, Confirmed, Completed
) : Serializable

// 3. Aviation Security Consulting
@Entity(tableName = "security_assessments")
data class SecurityAssessment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val targetDestination: String,
    val requestDate: String,
    val physicalGuardRequested: Boolean,
    val cyberAuditRequested: Boolean,
    val currentThreatLevel: String, // Low, Moderate, High, Severe (Pre-populated/assessed)
    val responseDetails: String,
    val status: String // Pending, Evaluated
) : Serializable

// 4. Crew Recruitment
@Entity(tableName = "crew_candidates")
data class CrewCandidate(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val fullName: String,
    val role: String, // Captain, First Officer, Cabin Attendant, Avionics Specialist
    val experienceYears: Int,
    val ratingsAndCerts: String, // e.g., G650 Type Rating, FAA First Class
    val availabilityStatus: String, // Available, In-Interview, Hired
    val rating: Double,
    val contactEmail: String
) : Serializable

// 6. Aircraft Maintenance Coordination (Note: Solution 5 was skipped in requirements)
@Entity(tableName = "maintenance_logs")
data class MaintenanceLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val aircraftTail: String,
    val taskName: String, // C-check, A-check, Engine Overhaul, Avionics Upgrade
    val dueDate: String,
    val maintenanceHub: String,
    val estimatedCostUsd: Double,
    val status: String // Outstanding, Scheduled, In Progress, Completed
) : Serializable

// 7. Fuel Procurement Services
@Entity(tableName = "fuel_quotes")
data class FuelQuote(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val airportIcao: String, // e.g., KLAX, LFPG
    val fboName: String, // Fixed Base Operator name
    val customPricePerGallon: Double,
    val gallonsRequested: Int,
    val requestDate: String,
    val status: String // Draft, Confirmed
) : Serializable

// 8. Charter Brokerage
@Entity(tableName = "charter_listings")
data class CharterListing(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val aircraftTail: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val departureDate: String,
    val ratePerHourUsd: Double,
    val isOpenForCharter: Boolean,
    val bookingsReceived: Int
) : Serializable

// 9. Regulatory Compliance
@Entity(tableName = "compliance_checks")
data class ComplianceCheck(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val requirementName: String, // e.g., Part 135 Inspection, Pilot Medical Cert, RVSM Authorization
    val authority: String, // FAA, EASA, CAA
    val dueDate: String,
    val complianceCategory: String, // Pilot, Aircraft, Operation
    val status: String // Compliant, Outstanding, Warning
) : Serializable

// 10. Private Hangar Development
@Entity(tableName = "hangar_developments")
data class HangarDevelopment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val airportHubCode: String, // e.g., KTEB (Teterboro), KOPF (Opa-locka)
    val hangarType: String, // Executive Hangar, Community Box hangar, Maintenance-ready hangar
    val sizeSqFt: Int,
    val buildingStage: String, // Planning, Groundbreaking, Structural Steel, Interior Fit-out, Complete
    val progressPercent: Int,
    val developmentAgentEmail: String
) : Serializable

// 11. Sustainability & Carbon-Offset Advisory
@Entity(tableName = "carbon_offset_logs")
data class CarbonOffsetLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val aircraftTail: String,
    val flightHours: Double,
    val sourceAirport: String,
    val destinationAirport: String,
    val tonsCo2Produced: Double,
    val offsetProjectName: String, // e.g., Amazon Reforestation, Renewable Wind Farm, Jet-A SAF Fuel blend
    val offsetCostUsd: Double,
    val offsetStatus: String // Outstanding, Compensated
) : Serializable
