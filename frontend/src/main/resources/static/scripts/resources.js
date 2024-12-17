import createUpdateResourceSubmit from "./createUpdateResourceSubmit.js";
import submitDeleteResource from "./submitDeleteResource.js";

const formModal = document.getElementById("formModal");

const deleteModal = document.getElementById("deleteModal");
const formModalContent = document.getElementsByClassName("formModalContent")[0];
const deleteModalContent =
  document.getElementsByClassName("deleteModalContent")[0];
const form = document.getElementById("form");
const openFormModalButton = document.getElementById("openFormModalButton");
const updateButtons = Array.from(
  document.getElementsByClassName("actionUpdate")
);
const deleteButtons = Array.from(
  document.getElementsByClassName("actionDelete")
);

const formInputs = Array.from(form.getElementsByTagName("input"));
const submitDeleteButton = document.getElementById("submitDeleteButton");
const deleteResourceUidPlaceholder = document.getElementById(
  "deleteResourceUidPlaceholder"
);
const cancelDeleteButton = document.getElementsByClassName("buttonCancel")[0];

const persistedResourceData = {
  selectedResource: null,
};

updateButtons.forEach((updateEl) =>
  updateEl.addEventListener("click", (e) => {
    persistedResourceData.selectedResource = {
      ...e.currentTarget.parentElement.dataset,
    };

    formInputs.forEach((input) => {
      const selectedResourceField =
        persistedResourceData.selectedResource[input.name.toLowerCase()];
      if (input.name != "resource" && selectedResourceField)
        input.value = selectedResourceField;
    });
    formModal.show();
  })
);

deleteButtons.forEach((delEl) =>
  delEl.addEventListener("click", (e) => {
    persistedResourceData.selectedResource = {
      ...e.currentTarget.parentElement.dataset,
    };
    deleteResourceUidPlaceholder.innerText =
      persistedResourceData.selectedResource.uid;
    deleteModal.show();
  })
);

submitDeleteButton.addEventListener("click", submitDeleteResource);

openFormModalButton.addEventListener("click", () => {
  formInputs.forEach((input) => (input.value = ""));
  persistedResourceData.selectedResource = null;
  formModal.show();
});

cancelDeleteButton.addEventListener("click", () => {
  deleteModal.close();
});

formModal.addEventListener("click", (e) => {
  if (!formModalContent.contains(e.target)) formModal.close();
});

deleteModal.addEventListener("click", (e) => {
  if (!deleteModalContent.contains(e.target)) deleteModal.close();
});

form.addEventListener("submit", createUpdateResourceSubmit);

export { persistedResourceData };
