package com.loyds.news

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.loyds.details.models.DetailsUiState
import com.loyds.details.ui.details.DetailsScreen
import com.loyds.model.data.Article
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest() {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenDetailsLoaded_givenFavouriteClick_callEvent() {

        val article = Article("1", "thumbnail", "section", "title", "body", 0, false)

        composeTestRule.setContentWithTheme {

            DetailsScreen(
                Modifier,
                DetailsUiState(article),
                { },
                { }
            )
        }

        composeTestRule
            .onNodeWithTag("Favourite_Button")
            .assertExists()
            .performClick()
    }
}
