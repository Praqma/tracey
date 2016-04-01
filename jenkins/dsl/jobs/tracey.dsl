import static JobHelpers.addScmBlock
import static JobHelpers.addDefaultSettings
import static JobHelpers.addpretestedIntegration

job('tracey-master-pretested'){
    addDefaultSettings(delegate)
    addScmBlock(delegate, 'https://github.com/Praqma/tracey.git', '100247a2-70f4-4a4e-a9f6-266d139da9db', ['*/ready/**'])
    addpretestedIntegration(delegate)
    label('tracey')
    triggers {
        scm('* * * * *')
    }
    steps {
        shell('cd jenkins/dsl && ./gradlew buildXml')
        shell('mvn pmd:pmd clean compile')
    }
    publishers {
        // Collect PMD report
        pmd('target/pmd.xml')
        // Collect compilation warnings
        warnings(['Java Compiler (javac)'])
        // Collect unit test results
        // archiveJunit('target/surefire-reports/*.xml')
    }
}
