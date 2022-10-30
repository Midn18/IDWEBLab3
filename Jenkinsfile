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
                junit allowEmptyResults: true, testResults: '**\\surefire-reports\\**.xml'
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