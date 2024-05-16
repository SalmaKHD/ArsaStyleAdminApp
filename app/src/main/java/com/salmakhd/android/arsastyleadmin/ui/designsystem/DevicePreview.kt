package com.salmakhd.android.arsastyleadmin.ui.designsystem

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(device = "spec:width=320dp,height=480dp,dpi=120", uiMode = Configuration.UI_MODE_NIGHT_YES, name= "Small Device, Night: Yes", showSystemUi = true)
@Preview(device = "spec:width=320dp,height=480dp,dpi=120", uiMode = Configuration.UI_MODE_NIGHT_NO, name= "Small Device, Night: No", showSystemUi = true)
annotation class SmallDevicePreview

@Preview(device = "spec:width=360.5dp,height=800dp,dpi=300", uiMode = Configuration.UI_MODE_NIGHT_YES, name= "Medium Device, Night: Yes", showSystemUi = true)
@Preview(device = "spec:width=360.5dp,height=800dp,dpi=300", uiMode = Configuration.UI_MODE_NIGHT_NO, name= "Medium Device, Night: No", showSystemUi = true)
annotation class MediumDevicePreview

@Preview(device = "spec:width=894dp,height=411dp,dpi=500", uiMode = Configuration.UI_MODE_NIGHT_YES, name="Large Device, Night: Yes", showSystemUi = true)
@Preview(device = "spec:width=894dp,height=411dp,dpi=500", uiMode = Configuration.UI_MODE_NIGHT_NO, name="Large Device, Night: No", showSystemUi = true)
annotation class LargeDevicePreview

@Preview(device = "spec:width=320dp,height=480dp,dpi=120", uiMode = Configuration.UI_MODE_NIGHT_YES, name= "Small Device, Night: Yes", showSystemUi = true)
@Preview(device = "spec:width=320dp,height=480dp,dpi=120", uiMode = Configuration.UI_MODE_NIGHT_NO, name= "Small Device, Night: No", showSystemUi = true)
@Preview(device = "spec:width=360.5dp,height=800dp,dpi=300", uiMode = Configuration.UI_MODE_NIGHT_YES, name= "Medium Device, Night: Yes", showSystemUi = true)
@Preview(device = "spec:width=360.5dp,height=800dp,dpi=300", uiMode = Configuration.UI_MODE_NIGHT_NO, name= "Medium Device, Night: No", showSystemUi = true)
@Preview(device = "spec:width=894dp,height=411dp,dpi=500", uiMode = Configuration.UI_MODE_NIGHT_YES, name="Large Device, Night: Yes", showSystemUi = true)
@Preview(device = "spec:width=894dp,height=411dp,dpi=500", uiMode = Configuration.UI_MODE_NIGHT_NO, name="Large Device, Night: No", showSystemUi = true)
annotation class AllSizeDevicePreview
