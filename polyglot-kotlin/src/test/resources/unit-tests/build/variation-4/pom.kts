project {
    build {
        pluginManagement {
            plugins {
                plugin {
                    groupId("org.apache.maven.plugins")
                    artifactId("maven-failsafe-plugin")
                    version("\${maven-failsafe-plugin.version}")
                    executions {
                        execution {
                            goals("integration-test")
                            phase("none")
                        }
                    }
                    configuration("""
                        <configuration>
                          <testFailureIgnore>true</testFailureIgnore>
                        </configuration>
                        """)
                }
                plugin {
                    groupId("org.apache.maven.plugins")
                    artifactId("maven-enforcer-plugin")
                    version("3.0.0.M2")
                    extensions(true)
                    inherited(true)
                    executions {
                        execution {
                            id("enforce-revision-is-set")
                            goals("enforce", "report")
                            inherited(true)
                            configuration("""
                                <configuration>
                                  <rules>
                                    <requireMavenVersion>
                                      <version>[3.5,)</version>
                                    </requireMavenVersion>
                                    <requireJavaVersion>
                                      <version>[1.8,9.0)</version>
                                    </requireJavaVersion>
                                  </rules>
                                </configuration>
                                """)
                        }
                    }
                    dependencies {
                        dependency("com.example:sample-lib")
                    }
                }
            }
        }
        plugins {
            plugin {
                groupId("org.apache.maven.plugins")
                artifactId("maven-compiler-plugin")
            }
            plugin {
                groupId("org.springframework.boot")
                artifactId("spring-boot-maven-plugin")
                version("2.1.2.RELEASE")
                extensions(true)
            }
        }
    }
}