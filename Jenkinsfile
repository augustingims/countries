pipeline {
    agent any

     tools{
         'org.jenkinsci.plugins.docker.commons.tools.DockerTool' 'docker'
     }


     environment {
 		PROJECT_VERSION = readMavenPom().getVersion()
		PROJECT_ARTIFACT_ID = readMavenPom().getArtifactId()
 		PROJECT_GROUP_ID = readMavenPom().getGroupId()
     }

    stages{
        stage('Checkout'){
            steps{
                checkout scm
            }
        }

        stage('Tests'){
            steps{
                withMaven(jdk: 'Jdk8', maven: 'maven-3.6.3') {
                    sh label: 'tests', script: './mvnw clean test'
                }
            }
        }

        stage('Analyse de la qualité'){
            steps{
                withMaven(jdk: 'Jdk8', maven: 'maven-3.6.3') {
                    withSonarQubeEnv('dev-sonarqube'){
                        sh label: 'static code analysis', script: 'mvn clean package sonar:sonar -Dsonar.projectName=countries/${BRANCH_NAME} -Dsonar.projectKey=countries:${BRANCH_NAME}'
                    }
                }
            }
        }

        stage('Packaging'){
            steps{
                withMaven(jdk: 'Jdk8', maven: 'maven-3.6.3') {
                    sh './mvnw clean package -Dskip.tests=true'
                }
            }
        }


        stage('Uploading artifacts on the releases repo'){
            when {
                branch 'master'
            }
            steps{
                nexusPublisher nexusInstanceId: "nexus.devops", nexusRepositoryId: "countries-releases", packages: [[$class: "MavenPackage", mavenAssetList: [[classifier: "", extension: "", filePath: "target/${PROJECT_ARTIFACT_ID}-${PROJECT_VERSION}.jar"]], mavenCoordinate: [artifactId: "countries", groupId: "com.example", packaging: "jar", version: "${PROJECT_VERSION}"]]]
            }
        }

        stage('Image docker Construction...'){
         	when {
         		branch 'master'
             }
             steps{
             	script {
	            	docker.withRegistry('http://registry.local'){
 	            		def dockerImage = docker.build("${PROJECT_ARTIFACT_ID}","--build-arg	JAR_FILE=target/${PROJECT_ARTIFACT_ID}-${PROJECT_VERSION}.jar .")
 	            		dockerImage.push('${PROJECT_VERSION}')
 	            	}
 				}
             }
         }

/*
        stage('Uploading artifacts on the snapshots repo'){
            when {
                not { branch 'master' }
            }
            steps{
                nexusPublisher nexusInstanceId: "nexus.snapshots", nexusRepositoryId: "countries-snapshots", packages: [[$class: "MavenPackage", mavenAssetList: [[classifier: "", extension: "", filePath: "target/${PROJECT_ARTIFACT_ID}-${PROJECT_VERSION}.jar"]], mavenCoordinate: [artifactId: "countries", groupId: "com.example", packaging: "jar", version: "${PROJECT_VERSION}"]]]
            }
        }

*/

       /*stage('Image docker Construction...'){
         	when {
         		anyOf { branch pattern: 'feature*'; branch pattern:tin'develop*' }
             }
             steps{
             	script {
 	            	docker.withRegistry('http://registry.local'){
 	            		def dockerImage = docker.build("${PROJECT_ARTIFACT_ID}","--build-arg	JAR_FILE=target/${PROJECT_ARTIFACT_ID}-${PROJECT_VERSION}.jar .")
 	            		dockerImage.push('${PROJECT_VERSION}')
 	            	}
 				}
             }
         }*/

    }

    post{
        always{
            echo 'Cleaning the workspace ...'
            cleanWs notFailBuild: true
        }
    }

}
