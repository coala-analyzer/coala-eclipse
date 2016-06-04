package com.coala.core.tests;

import static org.junit.Assert.assertEquals;

import com.coala.core.handlers.Plugin;
import com.coala.core.handlers.RemoveMarkers;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MarkerTest {

  private IProject      project;
  private Plugin        plugin;
  private RemoveMarkers removeMarkers;
  private IFile         file;

  /**
   * Setup before test.
   * @throws Exception exception
   */
  @Before
  public void setUp() throws Exception {
    String data = "package test_proj;\n\npublic class newClass {\n\n}";
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    project = root.getProject(TestUtils.generateRandomStr(5));
    if (!project.exists()) {
      project.create(null);
    }
    if (!project.isOpen()) {
      project.open(null);
    }
    file = project.getFile("test.java");
    if (!file.exists()) {
      byte[] bytes = data.getBytes();
      InputStream source = new ByteArrayInputStream(bytes);
      file.create(source, IResource.NONE, null);
    }
    plugin = new Plugin();
    removeMarkers = new RemoveMarkers();
  }

  @Test
  public void test() throws IOException, CoreException, ExecutionException {
    removeMarkers.removeAllMarkers(file);
    plugin.createCoolMarker(file, 0, 0, "First marker");
    plugin.createCoolMarker(file, 0, 0, "Second marker");
    IMarker[] markers = file.findMarkers("com.coala.core.coolproblem", true,
        IResource.DEPTH_INFINITE);
    assertEquals(markers.length, 2);
  }

  /**
   * Tear-down after test.
   * @throws CoreException exception
   */
  @After
  public void tearDown() throws CoreException {
    if (project.isOpen()) {
      project.close(null);
    }
    project.delete(true, true, null);
    ResourcesPlugin.getWorkspace().save(true, null);
  }
}
