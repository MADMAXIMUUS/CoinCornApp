package ru.coincorn.app.featureAuth.data.response

data class AuthStepResponse(
    val step: AuthStep
)

enum class AuthStep {
    ACCOUNT, DONE, VERIFY;

    override fun toString(): String = this.name

    companion object {
        fun String.toAuthStep(): AuthStep {
            return try {
                valueOf(this)
            } catch (e: Exception) {
                DONE
            }
        }
    }
}
