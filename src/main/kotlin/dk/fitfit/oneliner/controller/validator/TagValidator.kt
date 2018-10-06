package dk.fitfit.oneliner.controller.validator

import dk.fitfit.oneliner.domain.Tag
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

@Component
class TagValidator : Validator {
    override fun validate(target: Any?, errors: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Tag name is required.")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description", "Tag description is required.")
        val tag = target as Tag
        val whitespace = Regex("\\s")
        if (tag.name.contains(whitespace)) {
            errors.reject("required.noWhitespaceInTagName", "Tag name can't contain whitespace.")
        }
    }

    override fun supports(clazz: Class<*>) = Tag::class.java.isAssignableFrom(clazz)
}
