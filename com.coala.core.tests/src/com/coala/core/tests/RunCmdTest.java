package com.coala.core.tests;

import static org.junit.Assert.assertEquals;

import com.coala.core.handlers.Plugin;
import com.coala.core.handlers.RemoveMarkers;

import org.apache.commons.exec.ExecuteException;
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
import java.util.concurrent.TimeUnit;

public class RunCmdTest {

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
  public void test() throws InterruptedException, CoreException, ExecuteException, IOException {
    removeMarkers.removeAllMarkers(file);
    plugin.runcoalaOnFile(file, "CheckstyleBear");
    sleep(15);
    IMarker[] markers = file.findMarkers("com.coala.core.coolproblem", true,
        IResource.DEPTH_INFINITE);
    assertEquals(markers.length, 3);
  }

  /**
   * Tear-down after test.
   * @throws Exception exception
   */
  @After
  public void tearDown() throws Exception {
    if (project.isOpen()) {
      project.close(null);
    }
    project.delete(true, true, null);
    ResourcesPlugin.getWorkspace().save(true, null);
  }

  private void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }

}
