package com.coala.core.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;

public class RemoveMarkers extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IFile file = ((IFileEditorInput) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
        .getActivePage().getActiveEditor().getEditorInput()).getFile();
    removeAllMarkers(file);
    return null;
  }

  /**
   * Remove all markers of the type "com.coala.core.coolproblem" from the given file.
   * @param file IFile to remove markers from
   */
  public void removeAllMarkers(IFile file) {
    IMarker[] problems = null;
    IResource resource = (IResource) file;
    int depth = IResource.DEPTH_INFINITE;
    try {
      problems = resource.findMarkers(IMarker.PROBLEM, true, depth);

    } catch (CoreException ex) {
      ex.printStackTrace();
    }
    for (IMarker m : problems) {
      try {
        if (m.getType().equals("com.coala.core.coolproblem")) {
          m.delete();
        }
      } catch (CoreException ex) {
        ex.printStackTrace();
      }
    }
  }

}
