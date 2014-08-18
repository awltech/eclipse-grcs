package com.worldline.awltech.rcs4e.tools.internal.conditional;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.worldline.awltech.rcs4e.tools.internal.FormDataBuilder;

public class ConditionalTrackerDialog extends Dialog {

	private final String text;

	private boolean dontShowValue = false;

	public ConditionalTrackerDialog(Shell parentShell, String text) {
		super(parentShell);
		this.text = text;
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite background = new Composite(parent, SWT.NONE);
		background.setLayout(new FormLayout());

		Label label = new Label(background, SWT.WRAP);
		label.setText(this.text);
		FormDataBuilder.on(label).horizontal().top().width(400);

		Button doNotShowButton = new Button(background, SWT.CHECK);
		doNotShowButton.setText(ConditionalTrackerDialogMessages.DONTSHOW_BUTTON.value());
		doNotShowButton.setSelection(false);
		FormDataBuilder.on(doNotShowButton).left().top(label, 10);

		Button yesButton = new Button(background, SWT.PUSH);
		yesButton.setText(ConditionalTrackerDialogMessages.YES.value());
		FormDataBuilder.on(yesButton).right().bottom().top(doNotShowButton).width(80).height(25);

		Button noButton = new Button(background, SWT.PUSH);
		noButton.setText(ConditionalTrackerDialogMessages.NO.value());
		FormDataBuilder.on(noButton).right(yesButton).bottom().top(doNotShowButton).width(80).height(25);

		parent.pack();

		doNotShowButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ConditionalTrackerDialog.this.dontShowValue = ((Button) e.getSource()).getSelection();
			}
		});

		yesButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ConditionalTrackerDialog.this.setReturnCode(Window.OK);
				ConditionalTrackerDialog.this.close();
			}
		});

		noButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ConditionalTrackerDialog.this.setReturnCode(Window.CANCEL);
				ConditionalTrackerDialog.this.close();
			}
		});

		yesButton.setFocus();
		
		return background;
	}

	public boolean isDontShowValue() {
		return dontShowValue;
	}

	@Override
	protected Rectangle getConstrainedShellBounds(Rectangle preferredSize) {
		Shell workbenchShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		Rectangle bounds = workbenchShell.getBounds();
		Rectangle rect = this.getShell().getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		return new Rectangle(x, y, rect.width, rect.height);
	}
}
