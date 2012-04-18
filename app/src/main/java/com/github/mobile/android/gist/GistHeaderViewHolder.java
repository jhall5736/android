package com.github.mobile.android.gist;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.mobile.android.R.id;
import com.github.mobile.android.util.Time;
import com.madgag.android.listviews.ViewHolder;

import java.util.Date;

import org.eclipse.egit.github.core.Gist;

/**
 * Holder for a Gist header view
 */
public class GistHeaderViewHolder implements ViewHolder<Gist> {

    private final TextView created;

    private final TextView updated;

    private final TextView description;

    /**
     * Create view holder
     *
     * @param view
     */
    public GistHeaderViewHolder(final View view) {
        created = (TextView) view.findViewById(id.tv_gist_creation);
        updated = (TextView) view.findViewById(id.tv_gist_updated);
        description = (TextView) view.findViewById(id.tv_gist_description);
    }

    public void updateViewFor(Gist gist) {
        Date createdAt = gist.getCreatedAt();
        if (createdAt != null) {
            created.setText("Created " + Time.relativeTimeFor(createdAt));
            created.setVisibility(VISIBLE);
        } else
            created.setVisibility(GONE);

        Date updatedAt = gist.getUpdatedAt();
        if (updatedAt != null && !updatedAt.equals(createdAt)) {
            updated.setText("Updated " + Time.relativeTimeFor(updatedAt));
            updated.setVisibility(VISIBLE);
        } else
            updated.setVisibility(GONE);

        String desc = gist.getDescription();
        if (!TextUtils.isEmpty(desc))
            description.setText(desc);
        else
            description.setText(Html.fromHtml("<i>No description</i>"));
    }
}
