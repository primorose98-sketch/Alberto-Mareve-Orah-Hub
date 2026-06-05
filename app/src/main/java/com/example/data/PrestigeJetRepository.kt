package com.example.data

import kotlinx.coroutines.flow.Flow

class PrestigeJetRepository(private val dao: PrestigeJetDao) {

    // 1. Aircraft Management
    val allAircraft: Flow<List<Aircraft>> = dao.getAllAircraftFlow()
    suspend fun insertAircraft(aircraft: Aircraft) = dao.insertAircraft(aircraft)
    suspend fun deleteAircraft(aircraft: Aircraft) = dao.deleteAircraft(aircraft)

    // 2. Luxury Concierge
    val allConciergeRequests: Flow<List<ConciergeRequest>> = dao.getAllConciergeRequestsFlow()
    suspend fun insertConciergeRequest(request: ConciergeRequest) = dao.insertConciergeRequest(request)
    suspend fun deleteConciergeRequest(request: ConciergeRequest) = dao.deleteConciergeRequest(request)

    // 3. Security Assessments
    val allSecurityAssessments: Flow<List<SecurityAssessment>> = dao.getAllSecurityAssessmentsFlow()
    suspend fun insertSecurityAssessment(assessment: SecurityAssessment) = dao.insertSecurityAssessment(assessment)
    suspend fun deleteSecurityAssessment(assessment: SecurityAssessment) = dao.deleteSecurityAssessment(assessment)

    // 4. Crew Recruitment
    val allCrewCandidates: Flow<List<CrewCandidate>> = dao.getAllCrewCandidatesFlow()
    suspend fun insertCrewCandidate(candidate: CrewCandidate) = dao.insertCrewCandidate(candidate)
    suspend fun deleteCrewCandidate(candidate: CrewCandidate) = dao.deleteCrewCandidate(candidate)

    // 6. Maintenance Logs
    val allMaintenanceLogs: Flow<List<MaintenanceLog>> = dao.getAllMaintenanceLogsFlow()
    suspend fun insertMaintenanceLog(log: MaintenanceLog) = dao.insertMaintenanceLog(log)
    suspend fun deleteMaintenanceLog(log: MaintenanceLog) = dao.deleteMaintenanceLog(log)

    // 7. Fuel Quotes
    val allFuelQuotes: Flow<List<FuelQuote>> = dao.getAllFuelQuotesFlow()
    suspend fun insertFuelQuote(quote: FuelQuote) = dao.insertFuelQuote(quote)
    suspend fun deleteFuelQuote(quote: FuelQuote) = dao.deleteFuelQuote(quote)

    // 8. Charter Listings
    val allCharterListings: Flow<List<CharterListing>> = dao.getAllCharterListingsFlow()
    suspend fun insertCharterListing(listing: CharterListing) = dao.insertCharterListing(listing)
    suspend fun deleteCharterListing(listing: CharterListing) = dao.deleteCharterListing(listing)

    // 9. Compliance Checks
    val allComplianceChecks: Flow<List<ComplianceCheck>> = dao.getAllComplianceChecksFlow()
    suspend fun insertComplianceCheck(check: ComplianceCheck) = dao.insertComplianceCheck(check)
    suspend fun deleteComplianceCheck(check: ComplianceCheck) = dao.deleteComplianceCheck(check)

    // 10. Hangar Development
    val allHangarDevelopments: Flow<List<HangarDevelopment>> = dao.getAllHangarDevelopmentsFlow()
    suspend fun insertHangarDevelopment(hangar: HangarDevelopment) = dao.insertHangarDevelopment(hangar)
    suspend fun deleteHangarDevelopment(hangar: HangarDevelopment) = dao.deleteHangarDevelopment(hangar)

    // 11. Carbon Offset Logs
    val allCarbonOffsetLogs: Flow<List<CarbonOffsetLog>> = dao.getAllCarbonOffsetLogsFlow()
    suspend fun insertCarbonOffsetLog(offsetLog: CarbonOffsetLog) = dao.insertCarbonOffsetLog(offsetLog)
    suspend fun deleteCarbonOffsetLog(offsetLog: CarbonOffsetLog) = dao.deleteCarbonOffsetLog(offsetLog)
}
