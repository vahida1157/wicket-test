package com.mycompany.problems.fragmentpanelheading;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;
    private final Fragment frag1;
    private final Fragment frag2;

    private Form createForm;
    private Form submitForm;

    public HomePage(final PageParameters parameters) {
        super(parameters);
        createForm = new Form("createForm");
        submitForm = new Form("submitForm");
        createForm.add(new EmptyPanel("createPanel"));
        frag1 = new Fragment("createPanel", "frag1", createForm);
        frag2 = new Fragment("createPanel", "frag2", createForm);
        submitForm.add(new AjaxButton("frag1") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                createForm.addOrReplace(frag1);
                target.add(HomePage.this);
            }
        });
        submitForm.add(new AjaxButton("frag2") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                createForm.addOrReplace(frag2);
                target.add(HomePage.this);
            }
        });
        add(createForm, submitForm);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssReferenceHeaderItem.forReference(new CssResourceReference(HomePage.class, "bootstrap.min.css")));
    }
}
