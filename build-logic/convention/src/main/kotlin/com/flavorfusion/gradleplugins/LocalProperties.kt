package com.flavorfusion.gradleplugins

import org.gradle.api.Project
import java.util.Properties

object LocalProperties {
    private val cache = mutableMapOf<String, Properties>()

    private fun load(rootProject: Project): Properties =
        cache.getOrPut(rootProject.projectDir.absolutePath) {
            Properties().apply {
                val file = rootProject.file("local.properties")
                if (file.exists()) file.inputStream().use { load(it) }
            }
        }

    fun getKey(project: Project, key: String): String =
        load(project.rootProject).getProperty(key)
            ?: System.getenv(key)
            ?: error("Missing property: $key")
}

fun Project.getKey(key: String): String = LocalProperties.getKey(this, key)
