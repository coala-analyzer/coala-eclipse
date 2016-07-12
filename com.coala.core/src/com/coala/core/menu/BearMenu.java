package com.coala.core.menu;

import com.coala.core.utils.ExternalUtils;

import org.apache.commons.exec.ExecuteException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class BearMenu extends ContributionItem {

  public BearMenu() {
  }

  public BearMenu(String id) {
    super(id);
  }

  @Override
  public void fill(Menu menu, int index) {
    ArrayList<String> bears = new ArrayList<>();
    try {
      bears = getBears();
    } catch (IOException ex) {
      System.out.println("Bear list could not be fetched");
      ex.printStackTrace();
    }

    for (final String bear : bears) {
      MenuItem menuItem = new MenuItem(menu, SWT.CHECK, index);
      menuItem.setText(bear);
      menuItem.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent event) {
          IFile file = (IFile) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
              .getActivePart().getSite().getPage().getActiveEditor().getEditorInput()
              .getAdapter(IFile.class);
          try {
            ExternalUtils.runBearOnFile(file, bear);
          } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
          }
        }
      });
    }
  }

  private ArrayList<String> getBears() throws ExecuteException, IOException {
    JSONArray bearList = ExternalUtils.getAvailableBears();
    ArrayList<String> bearNames = new ArrayList<>();
    for (int i = 0; i < bearList.length(); i++) {
      bearNames.add(bearList.getJSONObject(i).getString("name"));
    }
    return bearNames;
  }
}
