import java.util.stream.Collectors

plugins {
    id("org.jetbrains.intellij") version "1.1.6"
    `java-library`
//    `kotlin-dsl`
//    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "com.voc.ide.plugin.tools"
version = "0.0.1"

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
    implementation("org.jsoup:jsoup:1.14.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")
}

val iu202121 = "D:\\SoftWare\\DeveloperKits\\JetBrains\\apps\\IDEA-U\\ch-0\\212.5080.55"

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
//    version.set("2021.2.1")
    pluginName.set("devtools-intellij-plugin")
    localPath.set(iu202121)
    updateSinceUntilBuild.set(false)
    downloadSources.set(false)
    plugins.set(arrayListOf("java", "JavaScript", "less", "sass", "stylus")) //
}

asciidoctorj {
//    sou

}

//asciidoctor {
//    sourceDir file('.')
//    sources {
//        include 'CHANGELOG.adoc'
//    }
//    outputDir file('build/docs')
//}

tasks {
    /* 清理项目额外删除 out 目录 */
    clean {
        delete("out")
    }

    /* adoc 文档生成 */
    asciidoctor {
        doFirst {
            file("build/docs").deleteRecursively()
        }
        setSourceDir(file("."))
        sources {
            include("CHANGELOG.adoc")
        }
        setOutputDir(file("build/docs"))
    }

//    withType<JavaCompile> {
//        sourceCompatibility = "11"
//        targetCompatibility = "11"
//    }

//    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//        kotlinOptions {
//            jvmTarget = "11"
//            freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=enable"
//        }
//    }

    patchPluginXml {
        setDependsOn(listOf(asciidoctor))
        pluginId.set(group)
        val description = file("src/main/resources/META-INF/description.html").readText(charset("UTF-8"))
        pluginDescription.set(description)
        val changelogFile = file("build/docs/CHANGELOG.html")
        if (changelogFile.exists()) {
            val changelog =  org.jsoup.Jsoup.parse(changelogFile.readText(charset("UTF-8")))
                .select("#releasenotes").first()
                ?.nextElementSibling()?.children()?.stream()?.map { e ->
                    e.html()
                        // issues id
                        .replace(
                            Regex("#([0-9]+)"),
                            "<a href=\"https://github.com/asciidoctor/asciidoctor-intellij-plugin/issues/\$1\">#\$1</a>"
                        )
                        // regex for GitHub usernames from https://github.com/shinnn/github-username-regex
                        .replace(
                            Regex("(?i)@([a-z\\d](?:[a-z\\d]|-(?=[a-z\\d])){0,38})"),
                            "<a href=\"https://github.com/\$1\">@\$1</a>"
                        )
                }?.collect(Collectors.joining("/n"))
            changeNotes.set(changelog)
        }

    }

    runPluginVerifier {
        localPaths.add(file(iu202121))
    }

    buildSearchableOptions {
        enabled.and(false)
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

