package com.coala.core.utils;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DialogUtils {
  /**
   * Dialog asking the user to install the coala framework. If the user answers in affirmative the
   * coala website is opened in the default web browser.
   */
  public static void installcoalaDialog() {
    URI coalaWebsite = null;
    try {
      coalaWebsite = new URI("http://coala.io");
    } catch (URISyntaxException ex) {
      ex.printStackTrace();
    }
    boolean ret = MessageDialog.openQuestion(null, "coala is not installed",
        "The coala framework is required to run code analysis. "
            + "Do you want to know how to install coala?");
    if (ret) {
      try {
        java.awt.Desktop.getDesktop().browse(coalaWebsite);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Dialog to get input from the user.
   */
  public static String getInputDialog(String title, String message) {
    InputDialog dlg = new InputDialog(null, title, message, null, null);
    String input = null;
    if (dlg.open() == Window.OK) {
      input = dlg.getValue();
    }
    return input;
  }

  /**
   * Dialog to show error to the user.
   */
  public static void showErrorDialog(final String title, final String message) {
    Display.getDefault().asyncExec(new Runnable() {
      @Override
      public void run() {
        MessageDialog.openError(null, title, message);
      }
    });
  }
}
