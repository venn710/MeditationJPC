package com.example.meditaionjpc

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditaionjpc.ui.theme.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditaionJPCTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = DeepBlue) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        GreetingSection(name = "Vennnnnn")

                        CategorySection(categories = listOf("Sweet Sleep", "Insomnia", "Depression"))

                        CurrentMeditationSection(timeDuration = "3-10 min", title = "Daily Thought")

                        FeaturedSection(features = listOf(
                            Feature(name = "Sleep meditation", gradients = listOf(BlueViolet1, BlueViolet2, BlueViolet3), type = FeatureType.Music),
                            Feature(name = "Tips for Sleeping", gradients = listOf(LightGreen1, LightGreen2,LightGreen3), type = FeatureType.VIDEO),
                            Feature(name = "Night island", gradients = listOf(OrangeYellow1, OrangeYellow2, OrangeYellow3), type = FeatureType.VIDEO),
                            Feature(name = "Calming sounds", gradients = listOf(Beige1, Beige2, Beige3), type = FeatureType.Music),
                            Feature(name = "Sleep meditation Again HAHA", gradients = listOf(BlueViolet1, BlueViolet2, BlueViolet3), type = FeatureType.VIDEO)
                        ))
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MeditaionJPCTheme {
        Greeting("Android")
    }
}

@Composable
fun GreetingSection(name: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(
                "Good Morning, $name",
                style = Typography.headlineMedium
            )

            Text(
                "We wish you gave a good day!",
                style = Typography.bodySmall
            )
        }

        Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "Search", tint = Color.White, modifier = Modifier.size(width = 24.dp, height = 24.dp))
    }
}


@Composable
fun CategorySection(categories: List<String>) {

    var selectedChip by remember {
        mutableStateOf(0)
    }

    LazyRow {
        itemsIndexed(categories) { index, item ->
            Box(
                Modifier
                    .padding(start = 12.dp, bottom = 20.dp, top = 20.dp)
                    .clip(shape = RoundedCornerShape(corner = CornerSize(30)))
                    .background(color = if (selectedChip == index) ButtonBlue else DarkerButtonBlue)
                    .clickable {
                        selectedChip = index
                    },
                contentAlignment = Alignment.Center) {
                Text(
                    item,
                    Modifier.padding(16.dp),
                    style = Typography.headlineMedium
                )
            }
        }
    }
}


@Composable
fun CurrentMeditationSection(title: String, timeDuration: String) {
    Box {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(corner = CornerSize(size = 16.dp)))
            .background(LightRed)
            .padding(horizontal = 12.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, style = Typography.headlineMedium)
                Text("Meditation • ${timeDuration}", style = Typography.bodyMedium.copy(color = TextWhite))
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(height = 36.dp, width = 36.dp)
                    .clip(CircleShape)
                    .background(ButtonBlue)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Play",
                    modifier = Modifier.size(width = 12.dp, height = 12.dp),
                    tint = TextWhite
                )
            }

        }
    }
}


@Composable
fun FeaturedSection(features: List<Feature>) {

    var gridCells = GridCells.Fixed(count = 2)

    Column(modifier = Modifier
        .padding(horizontal = 12.dp)) {

        Text(
            "Featured",
            style = Typography.headlineMedium.copy(color = TextWhite)
        )
        LazyVerticalGrid(
            columns = gridCells,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(features) { _, item ->
                FeaturedCard(feature = item)
            }
        }
    }
}


@Composable
fun FeaturedCard(feature: Feature) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(corner = CornerSize(size = 12.dp)))
            .background(brush = Brush.horizontalGradient(colors = feature.gradients))
            .padding(12.dp)
        ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = feature.name,
                style = Typography.headlineMedium.copy(color = TextWhite)
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Icon(
                    painter = if (feature.type == FeatureType.Music) painterResource(id = R.drawable.ic_headphone) else painterResource(
                        id = R.drawable.ic_videocam
                    ),
                    contentDescription = "FeatureType",
                    tint = TextWhite,
                    modifier = Modifier.size(width = 20.dp, height = 20.dp)
                )

                ButtonCustom(
                    title = "Start", backgroundColor = ButtonBlue
                )
            }
        }
    }
}



@Composable
fun ButtonCustom(title: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(corner = CornerSize(size = 12.dp)))
            .background(backgroundColor)
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
        contentAlignment = Alignment.Center) {
        Text(
            title,
            style = Typography.bodySmall.copy(color = TextWhite)
        )
    }
}