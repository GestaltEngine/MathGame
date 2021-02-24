package api

import config.CompiledConfiguration
import config.FunctionIdentifier
import expressiontree.ExpressionSubstitution
import expressiontree.NodeType
import mainpoints.TexVerificationResult
import mainpoints.checkFactsInTex
import mainpoints.compiledConfigurationBySettings
import mainpoints.configSeparator




fun checkSolutionInTex(
        originalTexSolution: String, //string with learner solution in Tex format

        //// individual task parameters:
        startExpressionIdentifier: String = "", //Expression, from which learner need to start the transformations
        targetFactPattern: String = "", //Pattern that specify criteria that learner's answer must meet
        additionalFactsIdentifiers: String = "", ///Identifiers split by configSeparator - task condition facts should be here, that can be used as rules only for this task

        endExpressionIdentifier: String = "", //Expression, which learner need to deduce
        targetFactIdentifier: String = "", //Fact that learner need to deduce. It is more flexible than startExpressionIdentifier and endExpressionIdentifier, allow to specify inequality like '''EXPRESSION_COMPARISON{(+(/(sin(x);+(1;cos(x)));/(+(1;cos(x));sin(x))))}{<=}{(/(2;sin(x)))}'''

        //// general configuration parameters
        //functions, which null-weight transformations allowed (if no other transformations), split by configSeparator
        //choose one of 2 api forms:
        wellKnownFunctions: List<FunctionIdentifier> = listOf(),
        wellKnownFunctionsString: String = "${configSeparator}0$configSeparator${configSeparator}1$configSeparator+$configSeparator-1$configSeparator-$configSeparator-1$configSeparator*$configSeparator-1$configSeparator/$configSeparator-1$configSeparator^$configSeparator-1",

        //functions, which null-weight transformations allowed with any other transformations, split by configSeparator
        //choose one of 2 api forms:
        unlimitedWellKnownFunctions: List<FunctionIdentifier> = wellKnownFunctions,
        unlimitedWellKnownFunctionsString: String = wellKnownFunctionsString,

        //expression transformation rules
        //choose one of api forms:
        expressionTransformationRules: List<ExpressionSubstitution> = listOf(), //full list of expression transformations rules
        expressionTransformationRulesString: String = "S(i, a, a, f(i))${configSeparator}f(a)${configSeparator}S(i, a, b, f(i))${configSeparator}S(i, a, b-1, f(i)) + f(b)", //function transformation rules, parts split by configSeparator; if it equals " " then expressions will be checked only by testing
        taskContextExpressionTransformationRules: String = "", //for expression transformation rules based on variables
        rulePacks: Array<String> = listOf<String>().toTypedArray(),

        maxExpressionTransformationWeight: String = "1.0",
        maxDistBetweenDiffSteps: String = "", //is it allowed to differentiate expression in one step
        scopeFilter: String = "", //subject scopes which user representation sings is used

        shortErrorDescription: String = "0" //make error message shorter and easier to understand: crop parsed steps from error description
): TexVerificationResult {
    val compiledConfiguration = createConfigurationFromRulePacksAndDetailSolutionCheckingParams(
            rulePacks,
            wellKnownFunctionsString,
            expressionTransformationRulesString,
            maxExpressionTransformationWeight,
            unlimitedWellKnownFunctionsString,
            taskContextExpressionTransformationRules,
            maxDistBetweenDiffSteps,
            scopeFilter,

            wellKnownFunctions,
            unlimitedWellKnownFunctions,
            expressionTransformationRules)
    return checkFactsInTex(
            originalTexSolution,
            startExpressionIdentifier,
            endExpressionIdentifier,
            targetFactIdentifier,
            targetFactPattern,
            additionalFactsIdentifiers,
            shortErrorDescription,
            compiledConfiguration)
}


fun checkSolutionInTexWithCompiledConfiguration(
        originalTexSolution: String, //string with learner solution in Tex format
        compiledConfiguration: CompiledConfiguration,

        //// individual task parameters:
        startExpressionIdentifier: String = "", //Expression, from which learner need to start the transformations
        targetFactPattern: String = "", //Pattern that specify criteria that learner's answer must meet
        additionalFactsIdentifiers: String = "", ///Identifiers split by configSeparator - task condition facts should be here, that can be used as rules only for this task

        endExpressionIdentifier: String = "", //Expression, which learner need to deduce
        targetFactIdentifier: String = "", //Fact that learner need to deduce. It is more flexible than startExpressionIdentifier and endExpressionIdentifier, allow to specify inequality like '''EXPRESSION_COMPARISON{(+(/(sin(x);+(1;cos(x)));/(+(1;cos(x));sin(x))))}{<=}{(/(2;sin(x)))}'''

        shortErrorDescription: String = "0" //make error message shorter and easier to understand: crop parsed steps from error description
): TexVerificationResult {
    return checkFactsInTex(
            originalTexSolution,
            startExpressionIdentifier,
            endExpressionIdentifier,
            targetFactIdentifier,
            targetFactPattern,
            additionalFactsIdentifiers,
            shortErrorDescription,
            compiledConfiguration)
}