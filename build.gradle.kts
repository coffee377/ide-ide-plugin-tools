import java.util.stream.Collectors

plugins {
    id("org.jetbrains.intellij") version "1.1.6"
    `java-library`
//    `kotlin-dsl`
//    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "com.voc.ide.plugin.tools"
version = "0.0.3"

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
    compileOnly("org.jsoup:jsoup:1.14.2")
    compileOnly(files("lib/idea-php-dotenv-plugin-2021.3.0.212.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")
}

val iu202121 = "D:\\SoftWare\\DeveloperKits\\JetBrains\\apps\\IDEA-U\\ch-0\\212.5080.55"

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
//    version.set("2021.2.1")
    localPath.set(iu202121)
    pluginName.set("devtools-intellij-plugin")
    updateSinceUntilBuild.set(false)
    downloadSources.set(false)
    plugins.set(arrayListOf("java", "JavaScript", "less", "sass", "stylus"))
}

asciidoctorj {

}


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
        sinceBuild.set("212.2.1")
        pluginId.set(project.group.toString())
        val description = file("src/main/resources/META-INF/description.html").readText(charset("UTF-8"))
        pluginDescription.set(description)
        val changelogFile = file("build/docs/CHANGELOG.html")
        if (changelogFile.exists()) {
            val changelog = org.jsoup.Jsoup.parse(changelogFile.readText(charset("UTF-8")))
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

    signPlugin {
        val chain = file("chain.crt")
        val privateKey = file("private.pem")
        if (chain.exists()) {
            certificateChain.set(chain.readText(charset("UTF-8")).trimIndent())
        }
        if (privateKey.exists()) {
            certificateChain.set(privateKey.readText(charset("UTF-8")).trimIndent())
        }
        password.set(ext.get("PRIVATE_KEY_PASSWORD").toString().trimIndent())
    }

    publishPlugin {
        token.set(ext.get("PUBLISH_TOKEN").toString().trimIndent())
    }

    runPluginVerifier {
//        failureLevel.set(
//            listOf(
//                org.jetbrains.intellij.tasks.RunPluginVerifierTask.FailureLevel.INVALID_PLUGIN,
//                org.jetbrains.intellij.tasks.RunPluginVerifierTask.FailureLevel.COMPATIBILITY_PROBLEMS,
//                org.jetbrains.intellij.tasks.RunPluginVerifierTask.FailureLevel.NOT_DYNAMIC
//            )
//        )
        ideVersions.set(listOf("2021.2.1"))
//        localPaths.add(file(iu202121))
    }

    buildSearchableOptions {
        enabled.and(false)
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

