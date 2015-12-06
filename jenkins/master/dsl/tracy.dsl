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
        shell('cd jenkins/master && ./build.sh')
        shell('mvn clean install pmd:pmd')
    }
    publishers {
        // Collect PMD report
        pmd('target/pmd.xml')
        // Collect compilation warnings
        warnings(['Java Compiler (javac)'])
    }
}
