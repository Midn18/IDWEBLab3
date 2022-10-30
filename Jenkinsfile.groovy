pipeline{
    agent any
    environment{
        ON_SUCCESS_SEND_EMAIL = true
        ON_FAILURE_SEND_EMAIL = true
    }

    stages{
        stage('Build'){
            steps{
                echo 'Building'
                bat 'mvn install -DskipTests --file ./backend'
                bat 'npm install %WORKSPACE%\\frontend'
            }
        }
        stage('Testing backend'){
            steps{
                echo 'Running backend tests'
                bat 'mvn test --file ./backend'
                post{
                    always{
                        junit '%WORKSPACE%\\backend\\target\\surefire-reports\\TEST-backend*.xml'
                    }
                }
              //  junit allowEmptyResults: true, testResults: '%WORKSPACE%\\backend\\target\\surefire-reports\\TEST-backend.xml'
                echo 'Backend tests finished execution'
            }
        }
        stage('Testing frontend'){
            steps{
                echo 'Testing'
              }
        }
    }
}