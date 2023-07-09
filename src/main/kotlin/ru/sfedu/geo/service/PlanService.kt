package ru.sfedu.geo.service

import org.springframework.stereotype.Service
import ru.sfedu.geo.config.AppProperties
import ru.sfedu.geo.model.Plan
import ru.sfedu.geo.repository.PlanRepository
import java.time.Clock
import java.time.LocalDate

@Service
class PlanService(
    private val clock: Clock,
    private val appProperties: AppProperties,
    private val planRepository: PlanRepository,
) {
    fun findByDeliverDate(deliveryDate: LocalDate) =
        planRepository.findByDeliveryDate(deliveryDate)

    fun findRecent(): List<Plan> = with(LocalDate.now(clock)) {
        planRepository.findByDeliveryDateBetween(
            minusDays(appProperties.daysBack.toLong()),
            plusDays(appProperties.daysForward.toLong())
        )
    }
}
