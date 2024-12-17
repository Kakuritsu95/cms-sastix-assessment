import {
  validateCreateFormFields,
  validateUpdateFormFields,
} from "./formValidationHelper.js";
import { persistedResourceData } from "./resources.js";
import {HOST, API_PATHS} from "./constants.js"

export default async function createUpdateResourceSubmit(e) {
  e.preventDefault();
  const formData = new FormData(e.target);
  Array.from(document.getElementsByClassName("error")).forEach((el) =>
    el.remove()
  );

  let formFieldErrors;

  if (persistedResourceData.selectedResource == null) {
    const formFieldErrors = validateCreateFormFields(formData);
    if (formFieldErrors.length > 0) {
      formFieldErrors.forEach((error) => {
        document
          .querySelectorAll(`[for=${error.field}]`)[0]
          .insertAdjacentHTML(
            "beforeEnd",
            `<span class="error"> ${error.message}</span>`
          );
      });
      return;
    }
    await fetch(HOST + API_PATHS.PREFIX + "/" + API_PATHS.RESOURCES, {
      method: "POST",
      body: formData,
    });
    window.location.reload();
  } else {
    formFieldErrors = validateUpdateFormFields(formData);
    if (formFieldErrors.length > 0) {
      formFieldErrors.forEach((error) => {
        document
          .querySelectorAll(`[for=${error.field}]`)[0]
          .insertAdjacentHTML(
            "beforeEnd",
            `<span class="error"> ${error.message}</span>`
          );
      });
      return;
    }
    formData.append("resourceUID", persistedResourceData.selectedResource.uid);
    formData.append(
      "author",
      persistedResourceData.selectedResource.resourceauthor
    );
    formData.append(
      "existingResourceURI",
      persistedResourceData.selectedResource.uri
    );

    await fetch(HOST + API_PATHS.PREFIX + "/" + API_PATHS.RESOURCES, {
      method: "PUT",
      body: formData,
    });
    window.location.reload();
  }
}
