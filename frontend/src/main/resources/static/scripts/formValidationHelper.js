const createResourceFormValidationRules = {
  resource: (fieldValue, formDataObject) =>
    ((!fieldValue.name && !formDataObject.resourceExternalURI) ||
      (fieldValue.name && formDataObject.resourceExternalURI)) &&
    "Insert either a resource or an external URI",
  resourceExternalURI: (fieldValue, formDataObject) =>
    ((!fieldValue && !formDataObject.resource.name) ||
      (fieldValue && formDataObject.resource.name)) &&
    "Insert either a resource or an external URI",
  resourceMediaType: (fieldValue, formDataObject) =>
    !fieldValue &&
    formDataObject.resourceExternalURI &&
    "Media type cant be empty if external URI is given",
  resourceTenantId: (fieldValue, formDataObject) =>
    !fieldValue && "TenantId cannot be empty",
  resourceName: (fieldValue) => !fieldValue && "Resource cannot be empty",
  resourceAuthor: (fieldValue) =>
    !fieldValue && "Resource author cannot be empty",
};

const updateResourceFormValidationRules = {
  resource: createResourceFormValidationRules.resource,
  resourceExternalURI: createResourceFormValidationRules.resourceExternalURI,
};

function validateCreateFormFields(formData) {
  const formFieldErrors = [];
  const formDataObject = Object.fromEntries(formData);

  Object.entries(createResourceFormValidationRules).forEach(
    ([field, validateRule]) => {
      const error = validateRule(formDataObject[field], formDataObject);
      if (error) formFieldErrors.push({ field: field, message: error });
    }
  );
  return formFieldErrors;
}

function validateUpdateFormFields(formData) {
  const formFieldErrors = [];
  const formDataObject = Object.fromEntries(formData);

  Object.entries(updateResourceFormValidationRules).forEach(
    ([field, validateRule]) => {
      const error = validateRule(formDataObject[field], formDataObject);
      if (error) formFieldErrors.push({ field: field, message: error });
    }
  );
  return formFieldErrors;
}

export { validateCreateFormFields, validateUpdateFormFields };
