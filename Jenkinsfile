pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
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
                // Run Maven on a Unix agent.
                bat "mvn clean test -Dtest=RestTests"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }

        stage('web stests') {
                    when {
                        expression{ return params.rest }
                    }
                    steps {
                        // Run Maven on a Unix agent.
                        bat "mvn clean test -Dtest=RestTests"

                        // To run Maven on a Windows agent, use
                        // bat "mvn -Dmaven.test.failure.ignore=true clean package"

                }

                post{
                always{
                allure([
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
                ])}
                }
    }
}
}