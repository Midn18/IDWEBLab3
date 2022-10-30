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
                bat 'mvn install --prefix ./backend'
                bat 'mvn spring-boot:run --prefix ./backend'
                bat 'npm install --prefix ./frontend'
                bat 'ng serve --prefix ./frontend'
            }
        }
        stage('Test'){
            steps{
                echo 'Testing'
            }
        }
    }
}