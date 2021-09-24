plugins {
    id("org.jetbrains.intellij") version "1.1.6"
    `java-library`
    `kotlin-dsl`
}

group = "com.voc.ide.plugin.tools"
version = "1.0.0"

sourceSets {
    main {
        java {
            srcDirs("src/main/gen")
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(files("lib/wxapp-intellij-0.2.10-4aaf09b.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")
}

val iu202121 = "D:\\SoftWare\\DeveloperKits\\JetBrains\\apps\\IDEA-U\\ch-0\\212.5080.55"

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
//    version.set("2021.2.1")
    localPath.set(iu202121)
    plugins.set(arrayListOf("java", "JavaScript", "less", "sass", "stylus")) //
//    plugins.a
    updateSinceUntilBuild.set(false)
    downloadSources.set(false)
}
tasks {
    patchPluginXml {
        pluginId.set(project.group.toString())
        pluginDescription.set("测试的")
        changeNotes.set(
            """
            Add change notes here.<br>
            <em>most HTML tags may be used</em>        """.trimIndent()
        )
    }

    runPluginVerifier {
        localPaths.add(file(iu202121))
    }

    buildSearchableOptions {
//        enabled.and()
//        enabled
        enabled.and(false)
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

