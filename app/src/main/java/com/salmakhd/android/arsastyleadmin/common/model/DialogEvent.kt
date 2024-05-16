package com.salmakhd.android.arsastyleadmin.common.model

sealed interface NirvanaDialogEvent {
    object DialogDismissed: NirvanaDialogEvent
    object ConfirmationDialogButtonClicked: NirvanaDialogEvent
    object DialogCancellationButtonClicked: NirvanaDialogEvent
}