package com.example.alumnihivev11.Elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alumnihivev11.R


@Composable
fun MentorshipBanner(
    modifier: Modifier = Modifier,
    text: String,
    titleText: String,
    icon: Int,
    shape: Shape = MaterialTheme.shapes.medium,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onClicked: () -> Unit,
) {
    var clicked by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                clicked = !clicked
                onClicked()

            },
        shape = shape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor

    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier.fillMaxWidth().height(150.dp)
                .padding(start = 10.dp,
                    top = 4.dp,
                    bottom = 4.dp,
                    end = 4.dp),
            shape = shape,
            onClick = {
                onClicked()
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(5.dp)
                ) {
                    Icon(
                        Icons.Default.Book, contentDescription = "Blog Icon",
                        modifier = Modifier.size(width = 30.dp, height = 30.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    )

                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Google Button",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(width = 30.dp, height = 30.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(25.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(8.dp)
                ) {
                    Text(
                        text = titleText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 2
                    )
                }

                Row(
                    modifier = Modifier
                        .height(120.dp)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = text,
                        fontSize = 13.sp,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(
                        modifier = Modifier.fillMaxHeight().weight(1f)
                    )
                    Icon(Icons.Default.ArrowForward, contentDescription = "To the Community",
                        modifier = Modifier.fillMaxHeight().width(30.dp))
                    Spacer(
                        modifier = Modifier.fillMaxHeight().width(20.dp)
                    )

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "To the Community",
                        modifier = Modifier.fillMaxHeight().width(30.dp))
                    Spacer(
                        modifier = Modifier.fillMaxHeight().width(20.dp)
                    )
                }
            }

        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun MentorshipBannerPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BlogsCards(
            text = "Python Learning",
            titleText = "Python",
            icon = R.drawable.ic_google_logo
        ) { }
    }
}

