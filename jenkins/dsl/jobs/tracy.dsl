import static JobHelpers.addScmBlock
import static JobHelpers.addDefaultSettings
import static JobHelpers.addpretestedIntegration

job('tracy-master-pretested'){
    addDefaultSettings(delegate)
    addScmBlock(delegate, 'git@github.com:Andrey9kin/tracy.git', 'jenkins', ['*/ready/**'])
    addpretestedIntegration(delegate)
    triggers {
        scm('* * * * *')
    }
    steps {
        shell('cd jenkins/dsl && ./gradlew buildXml')
        shell('mvn pmd:pmd clean install')
    }
    publishers {
        // Collect PMD report
        pmd('target/pmd.xml')
        // Collect compilation warnings
        warnings(['Java Compiler (javac)'])
        // Collect unit test results
        archiveJunit('target/surefire-reports/*.xml')
    }
}
