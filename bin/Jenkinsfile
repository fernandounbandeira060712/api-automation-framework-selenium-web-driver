pipeline {
    agent { node { label 'generic-node' } }

    // Execução agendada (exemplo)
    triggers { cron('H 9,11,13,15,17 * * 1-5') }

    parameters {
        choice(
            name: 'BRANCH',
            choices: ['development', 'main'],
            description: 'Selecione a branch'
        )
        choice(
            name: 'SUITE',
            choices: [
                'suite-login.xml',
                'suite-admin.xml',
                'suite-relatorios.xml',
                'suite-geral.xml'
            ],
            description: 'Selecione a suíte de testes'
        )
        booleanParam(
            name: 'GENERATE_REPORT',
            defaultValue: true,
            description: 'Gerar relatório Allure?'
        )
    }

    environment {
        EVID_DIRS  = "allure-results reports screenshots"
        EVID_ZIP   = "evidences-${env.BUILD_NUMBER}.zip"

        EMAIL_TO   = "qa-team@example.com"

        MAVEN_BIN  = "/path/to/maven/bin/mvn"
        CHROME_FLAGS = '--headless --no-sandbox --disable-gpu'

        SCM_BASE = "https://git.example.com"
        PROJECT  = "qa-project"
        REPO     = "automation-repo"
    }

    stages {

        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: "${params.BRANCH}",
                    credentialsId: 'git-credentials',
                    url: "${SCM_BASE}/${PROJECT}/${REPO}.git"
            }
        }

        stage('Build & Tests (rerun falhas)') {
            steps {
                catchError(buildResult: 'FAILURE') {
                    sh """
                        "${env.MAVEN_BIN}" \
                        -Dsurefire.rerunFailingTestsCount=2 \
                        -DcustomProperties="${env.CHROME_FLAGS}" \
                        -DSUITE=${params.SUITE} \
                        clean test
                    """
                }
            }
        }

        stage('Processar Resultados') {
            steps {
                sh '''
                    if [ -d "allure-results" ]; then
                        echo "Processando resultados..."
                        # Exemplo de ajuste de dados
                    fi
                '''
            }
        }

        stage('Allure Report') {
            when {
                expression { params.GENERATE_REPORT }
            }
            steps {
                allure results: [[path: 'allure-results']]
            }
        }

        stage('Empacotar Evidências') {
            steps {
                sh """
                    zip -r "${EVID_ZIP}" ${EVID_DIRS} || true
                """
            }
        }

        stage('Arquivar Artefatos') {
            steps {
                archiveArtifacts artifacts: "${EVID_ZIP}, allure-results/**, reports/**, screenshots/**",
                                  allowEmptyArchive: true
            }
        }

        stage('Gerar Métricas') {
            steps {
                sh """
                    echo "Extraindo métricas dos resultados..."
                """
            }
        }

        stage('Criar Pull Request') {
            when {
                expression {
                    currentBuild.currentResult == 'SUCCESS' && params.BRANCH == 'development'
                }
            }
            steps {
                echo "Criando PR automaticamente via API..."
            }
        }

        stage('Notificação de Falha') {
            when {
                expression { currentBuild.currentResult in ['FAILURE', 'UNSTABLE'] }
            }
            steps {
                mail(
                    to: EMAIL_TO,
                    subject: "Build com falha",
                    body: "Verifique os relatórios e evidências."
                )
            }
        }

        stage('Notificação de Sucesso') {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                mail(
                    to: EMAIL_TO,
                    subject: "Build com sucesso",
                    body: "Pipeline executada com sucesso. PR pronto para aprovação."
                )
            }
        }
    }

    post {
        always {
            echo "Resultado final: ${currentBuild.currentResult}"
        }
    }
}
