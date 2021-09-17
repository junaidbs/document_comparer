

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
        jar -cvf test.war .
    }
}
stage('Deploy - Production') {
    steps {
        bat './deploy production'
    }
}
}
