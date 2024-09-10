pipeline {
    agent any

    tools {
        jdk "18"
        maven "3.9.9"
    }

    parameters {
        booleanParam(defaultValue: true, description: 'run rest tests',name: 'rest')
        booleanParam(defaultValue: false, description: 'run web tests',name: 'web')
        }
    stages {
        stage('rest stests') {
            when {
                expression{ return params.rest }
            }
            steps {
                bat "mvn clean test -Dtest=RestTests"
            }
        }
        stage('web stests') {
                    when {
                        expression{ return params.web }
                    }
                    steps {
                        bat "mvn clean test -Dtest=RestTests"
                }}

        stage('Checkstyle Analysis') {
                    steps {
                        // Run Checkstyle analysis with Maven
                        bat 'mvn checkstyle:checkstyle'
                    }
                }

        stage('Publish Checkstyle Results') {
                            steps {
                                // Publish Checkstyle results to Jenkins
                                // Assuming the Checkstyle report is generated in target/site/checkstyle.xml
                                recordIssues(tools: [checkStyle(pattern: 'target/site/checkstyle.xml')])
                            }
                        }


        post{
            always{
                allure([
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
                ]),
                archiveArtifacts artifacts: 'target/site/checkstyle.xml', allowEmptyArchive: true}
                }
        }

}