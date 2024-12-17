import { persistedResourceData } from "./resources.js";
import { HOST, API_PATHS } from  "./constants.js"



export default async function submitDeleteResource() {
  await fetch(HOST + API_PATHS.PREFIX + "/" + API_PATHS.RESOURCES, {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      resourceUID: persistedResourceData.selectedResource.uid,
      resourceAuthor: persistedResourceData.selectedResource.resourceauthor,
    }),
  });
  window.location.reload();
}
