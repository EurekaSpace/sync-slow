package io.anan.eurekaspace.sync_slow

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SyncSlowApplication

fun main(args: Array<String>) {
	runApplication<SyncSlowApplication>(*args)
}
