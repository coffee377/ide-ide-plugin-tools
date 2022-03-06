import java.util.stream.Collectors

plugins {
    `java-library`
    id("org.jetbrains.intellij") version "1.1.6"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    id("org.jetbrains.changelog") version "1.3.0"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.voc.ide.plugin.tools"
version = "0.0.5-alpha.1"

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

dependencyManagement {
    dependencies {
        dependency("org.springframework:spring-core:4.0.3.RELEASE")
//        dependency(mapOf(
//            "group" to "org.springframework",
//            "name" to "spring-core",
//            "version" to "4.0.3.RELEASE"
//        ))
    }
}

dependencies {
//    compileOnly("org.jsoup:jsoup:1.14.3")
//    implementation("org.springframework:spring-core")
    compileOnly(files("lib/idea-php-dotenv-plugin-2021.3.0.212.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

var iu202121 = "D:\\SoftWare\\DeveloperKits\\JetBrains\\apps\\IDEA-U\\ch-0\\212.5080.55"
var iu202124 = "D:\\SoftWare\\DeveloperKits\\JetBrains\\apps\\IDEA-U\\ch-0\\212.5712.43"
var iu20213 = "D:\\SoftWare\\DeveloperKits\\JetBrains\\apps\\IDEA-U\\ch-0\\213.5744.223"

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
//    version.set("2021.2.1")
    localPath.set(iu202124)
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

    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    /* adoc 文档生成 */
    asciidoctor {
        doFirst {
            file("build/docs").deleteRecursively()
        }
        setSourceDir(file("."))
        sources {
            include("*.adoc")
        }
        setOutputDir(file("build/docs"))
    }

    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=enable"
        }
    }

    patchPluginXml {
        setDependsOn(listOf(asciidoctor))
        sinceBuild.set("212.2.1")
        pluginId.set(project.group.toString())

        val descriptionFile = file("build/docs/README.html")
        if (descriptionFile.exists()) {
            val description = org.jsoup.Jsoup.parse(descriptionFile.readText(charset("UTF-8")))
                .select("#content").first()?.children()?.stream()?.map { e -> e.html() }?.collect(
                    Collectors.joining("")
                )
            pluginDescription.set(description)
        }

        val changelogFile = file("build/docs/CHANGELOG.html")
        if (changelogFile.exists()) {
            val changelog = org.jsoup.Jsoup.parse(changelogFile.readText(charset("UTF-8")))
                .select("#releasenotes").first()
                ?.nextElementSibling()?.children()?.stream()?.map { e ->
                    e.html()
                        // issues id
                        .replace(
                            Regex("#([0-9]+)"),
                            "<a href=\"https://github.com/coffee377/ide-plugin-tools/issues/\$1\">#\$1</a>"
                        )
                        // regex for GitHub usernames from https://github.com/shinnn/github-username-regex
                        .replace(
                            Regex("(?i)@([a-z\\d](?:[a-z\\d]|-(?=[a-z\\d])){0,38})"),
                            "<a href=\"https://github.com/\$1\">@\$1</a>"
                        )
                }?.collect(Collectors.joining(""))
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
        // pluginVersion is based on the SemVer (https://semver.org) and supports pre-release labels, like 2.1.7-alpha.3
        // Specify pre-release label to publish the plugin in a custom Release Channel automatically. Read more:
        // https://plugins.jetbrains.com/docs/intellij/deployment.html#specifying-a-release-channel
        val channel = project.version.toString().split("-")
            .getOrElse(1) { "default" }.split(".").first()
        channels.set(listOf(channel))
    }

    runPluginVerifier {
        ideVersions.set(listOf("2021.2.1"))
    }

    buildSearchableOptions {
        enabled.and(false)
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}


