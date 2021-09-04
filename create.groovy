import groovy.json.*
pipeline {
  agent any

  environment {
    USE_JDK = 'true'
    JDK ='c:/Java/jdk1.8'
  }
  
parameters {
  choice choices: ['T1', 'T2', 'T3'], name: 'Template'
  choice choices: ['G1', 'G2', 'G3'], name: 'Groupname'
  choice choices: ['U1', 'U2', 'U3'], name: 'username'
  choice choices: ['SNOW_Dev', 'SNOW_Test', 'SNOW_Prod'], name: 'SNOW'
  text defaultValue: 'Test', name: 'multidesc'
  string defaultValue: 'Test', name: 'Description'
}

  stages {
        stage('APi HealthCheck') {
          steps {
              script {
                configfile = mail()
               echo configfile['st']
                
              }
               
            }
        }
       
    }
}

def mail() {
      configFileProvider([configFile(fileId: 'f246c43c-d3e7-4ff4-9f82-583d492b06b9', variable: 'jsonfile')]) 
                {
                    script {
                        def file = readJSON file: jsonfile, returnPojo: true
                        def keyList = file['std']
                        return keyList
                    }
                }
}
