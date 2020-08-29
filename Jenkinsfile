pipeline {
  agent {
    dockerfile {
      filename 'dockerfile'
    }

  }
  stages {
    stage('Collect Variables') {
      steps {
        git(url: 'https://github.com/yadavshivam8091/LoadBalancer.git', branch: 'master', changelog: true, credentialsId: 'jenkins', poll: true)
      }
    }

    stage('Build Image') {
      steps {
        sh 'docker build .'
        build(job: 'Push', propagate: true)
      }
    }

  }
}