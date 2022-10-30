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
        stage('TESTING_BACKEND'){
            steps{
                echo 'Testing'
                junit 'backend/target/surefire-reports/test-result.xml'
            }
        }
        stage('TESTING_FRONTEND'){
            steps{
                echo 'Testing'
              }
        }
    }
}