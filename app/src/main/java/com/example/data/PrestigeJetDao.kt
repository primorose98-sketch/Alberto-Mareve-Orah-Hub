package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PrestigeJetDao {

    // 1. Aircraft Management
    @Query("SELECT * FROM aircraft ORDER BY id DESC")
    fun getAllAircraftFlow(): Flow<List<Aircraft>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAircraft(aircraft: Aircraft)

    @Delete
    suspend fun deleteAircraft(aircraft: Aircraft)


    // 2. Luxury Concierge
    @Query("SELECT * FROM concierge_requests ORDER BY id DESC")
    fun getAllConciergeRequestsFlow(): Flow<List<ConciergeRequest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConciergeRequest(request: ConciergeRequest)

    @Delete
    suspend fun deleteConciergeRequest(request: ConciergeRequest)


    // 3. Security Assessments
    @Query("SELECT * FROM security_assessments ORDER BY id DESC")
    fun getAllSecurityAssessmentsFlow(): Flow<List<SecurityAssessment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSecurityAssessment(assessment: SecurityAssessment)

    @Delete
    suspend fun deleteSecurityAssessment(assessment: SecurityAssessment)


    // 4. Crew Recruitment
    @Query("SELECT * FROM crew_candidates ORDER BY rating DESC")
    fun getAllCrewCandidatesFlow(): Flow<List<CrewCandidate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrewCandidate(candidate: CrewCandidate)

    @Delete
    suspend fun deleteCrewCandidate(candidate: CrewCandidate)


    // 6. Maintenance Logs
    @Query("SELECT * FROM maintenance_logs ORDER BY id DESC")
    fun getAllMaintenanceLogsFlow(): Flow<List<MaintenanceLog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaintenanceLog(log: MaintenanceLog)

    @Delete
    suspend fun deleteMaintenanceLog(log: MaintenanceLog)


    // 7. Fuel Quotes
    @Query("SELECT * FROM fuel_quotes ORDER BY id DESC")
    fun getAllFuelQuotesFlow(): Flow<List<FuelQuote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuelQuote(quote: FuelQuote)

    @Delete
    suspend fun deleteFuelQuote(quote: FuelQuote)


    // 8. Charter Listings
    @Query("SELECT * FROM charter_listings ORDER BY id DESC")
    fun getAllCharterListingsFlow(): Flow<List<CharterListing>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharterListing(listing: CharterListing)

    @Delete
    suspend fun deleteCharterListing(listing: CharterListing)


    // 9. Compliance Checks
    @Query("SELECT * FROM compliance_checks ORDER BY dueDate ASC")
    fun getAllComplianceChecksFlow(): Flow<List<ComplianceCheck>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComplianceCheck(check: ComplianceCheck)

    @Delete
    suspend fun deleteComplianceCheck(check: ComplianceCheck)


    // 10. Hangar Development
    @Query("SELECT * FROM hangar_developments ORDER BY progressPercent DESC")
    fun getAllHangarDevelopmentsFlow(): Flow<List<HangarDevelopment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHangarDevelopment(hangar: HangarDevelopment)

    @Delete
    suspend fun deleteHangarDevelopment(hangar: HangarDevelopment)


    // 11. Carbon Offset Logs
    @Query("SELECT * FROM carbon_offset_logs ORDER BY id DESC")
    fun getAllCarbonOffsetLogsFlow(): Flow<List<CarbonOffsetLog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarbonOffsetLog(offsetLog: CarbonOffsetLog)

    @Delete
    suspend fun deleteCarbonOffsetLog(offsetLog: CarbonOffsetLog)
}
