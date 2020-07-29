apply(plugin = "maven-publish")

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(components.findByName("android"))
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>(LibraryConstants.PUBLICATION_NAME) {
            run {
                groupId = LibraryConstants.ARTIFACT_GROUP
                artifactId = LibraryConstants.ARTIFACT_NAME
                version = LibraryConstants.VERSION
                from(components.findByName("android"))
                artifact(sourcesJar)
                artifact("$buildDir/outputs/aar/app-release.aar")
                pom {
                    name.set(LibraryConstants.ARTIFACT_NAME)
                    description.set(LibraryConstants.POM_DESCRIPTION)
                    url.set(LibraryConstants.POM_URL)
                    licenses {
                        license {
                            name.set(LibraryConstants.LICENSE_NAME)
                            url.set(LibraryConstants.LICENSE_URL)
                        }
                    }
                    developers {
                        developer {
                            id.set(LibraryConstants.DEVELOPER)
                            name.set(LibraryConstants.DEVELOPER_NAME)
                        }
                    }
                }
            }
        }
    }
}