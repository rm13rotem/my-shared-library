def sonarCreateProject(String projectKey) {

    withCredentials([
        string(credentialsId: 'sonnar-scanner', variable: 'SONAR_TOKEN')
    ]) {

        withSonarQubeEnv('SonarQubeScanner') {

            sh """
                curl -s -u ${SONAR_TOKEN}: \
                -X POST "${SONAR_HOST_URL}/api/projects/create" \
                -d "project=${projectKey}&name=${projectKey}" || true
            """
        }
    }
}

def sonarLocalScan() {

    def scannerHome = tool 'SonarQubeScanner'

    withSonarQubeEnv('SonarQubeScanner') {

        sh """
            ${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=${env.JOB_NAME} \
            -Dsonar.projectName=${env.JOB_NAME} \
            -Dsonar.sources=. \
            -Dsonar.sourceEncoding=UTF-8 \
            -Dsonar.exclusions=**/node_modules/**,**/bin/**,**/obj/**
        """
    }
}

return this
