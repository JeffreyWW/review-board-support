package com.guyazhou.tools.plugin.reviewboard.model.draft;

import com.guyazhou.tools.plugin.reviewboard.model.Response;

/**
 * Draft response
 * Created by Yakov on 2017/1/12.
 */
public class DraftResponse extends Response {

    private Draft draft;

    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }
}
