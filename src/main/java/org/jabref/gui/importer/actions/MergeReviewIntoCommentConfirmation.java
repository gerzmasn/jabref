package org.jabref.gui.importer.actions;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import org.jabref.gui.BasePanel;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.entry.BibEntry;

public class MergeReviewIntoCommentConfirmation {

    private final BasePanel panel;

    public MergeReviewIntoCommentConfirmation(BasePanel panel) {
        this.panel = panel;
    }

    public boolean askUserForMerge(List<BibEntry> conflicts) {
        List<String> bibKeys = conflicts.stream()
                .map(BibEntry::getCiteKeyOptional)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        int answer = JOptionPane.showConfirmDialog(
                panel,
                String.join(",\n", bibKeys) + " " +
                        Localization.lang("has/have both a 'Comment' and a 'Review' field.") + "\n" +
                        Localization.lang("Since the 'Review' field was deprecated in JabRef 4.2, these two fields are about to be merged into the 'Comment' field.") + "\n" +
                        Localization.lang("The conflicting fields of these entries will be merged into the 'Comment' field."),
                Localization.lang("Review Field Migration"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);

        return 0 == answer;
    }
}