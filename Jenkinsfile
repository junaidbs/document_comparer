

node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'mavensonar';
    withSonarQubeEnv() {
      bat "${mvn}/bin/mvn sonar:sonar"
    }
  }
  stage('Deploy - Staging') {
    steps {
        bat './deploy staging'
        bat './run-smoke-tests'
    }
}
stage('Deploy - Production') {
    steps {
        bat './deploy production'
    }
}
}
