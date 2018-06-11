pipeline {
	agent { 
		docker { 
			image 'maven:3.3.3' 
		} 
	}
	stages {
		stage('build') {
			steps {
				sh 'mvn -B -DskipTests clean package'
			}
		}
		
		stage('test') {
			steps {
				sh 'mvn clean verify'
			}
			post{
				always{
					step([$class: 'PmdPublisher', pattern: '**/target/pmd.xml'])
					step([$class: 'FindBugsPublisher', pattern: '**/findbugsXml.xml'])
				}
			}
		}
	}
}