pipeline{
    agent any
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
                echo "Testing frontend ${TESTING_FRONTEND}"
            }
        }
        stage('Delete workspace'){
            when{
                expression { CLEAN_WORKSPACE == "true" }
            }
            steps{
                echo "Deleting workspace ${CLEAN_WORKSPACE}"
                deleteDir()
            }
        }
    }
    post{
        success{
            mail body: "Build finished successfully, see ${BUILD_URL}",
            subject: "Jenkins Build ${currentBuild.currentResult} : Job ${env.JOB_NAME} Build Number ${env.BUILD_NUMBER}",
            to: 'mihail.danilenco18@gmail.com'
        }
        failure{
            mail body: "Build finished successfully, see ${BUILD_URL}",
            subject: "Jenkins Build ${currentBuild.currentResult} : Job ${env.JOB_NAME} Build Number ${env.BUILD_NUMBER}",
            to: 'mihail.danilenco18@gmail.com'
        }
    }
}