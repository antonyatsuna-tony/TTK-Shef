package ru.ttkshef
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Step(val title:String,val text:String,val mode:String,val time:String)

@Composable
fun App(){
 var dish by remember{mutableStateOf("")}
 var out by remember{mutableStateOf("300")}
 var result by remember{mutableStateOf(false)}
 MaterialTheme(colorScheme=lightColorScheme(primary=androidx.compose.ui.graphics.Color(0xFF6947F5))){
  if(!result){
   Column(Modifier.padding(24.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
    Text("ТТК Шеф",style=MaterialTheme.typography.headlineLarge)
    Text("Создание технологической карты")
    OutlinedTextField(dish,{dish=it},Modifier.fillMaxWidth(),label={Text("Название блюда")})
    OutlinedTextField(out,{out=it},Modifier.fillMaxWidth(),label={Text("Выход, г")})
    Button({result=true},Modifier.fillMaxWidth()){Text("✨ Создать ТТК")}
    Text("Прототип. Следующий этап — подключение настоящего ИИ.")
   }
  } else {
   val name=dish.ifBlank{"Паста Карбонара"}
   val steps=listOf(
    Step("Подготовка сырья","Проверить качество сырья и взвесить ингредиенты по утверждённой рецептуре.","—","—"),
    Step("Приготовление пасты","Отварить пасту в кипящей подсоленной воде до состояния al dente.","98–100 °C","8–10 мин"),
    Step("Обработка основного сырья","Выполнить тепловую обработку до требуемой степени готовности.","160–170 °C","4–5 мин"),
    Step("Приготовление соуса","Смешать компоненты и прогреть без интенсивного кипения.","70–75 °C","2–3 мин"),
    Step("Соединение компонентов","Соединить подготовленные компоненты и довести до однородной консистенции.","70–80 °C","1–2 мин"),
    Step("Оформление и подача","Оформить блюдо согласно стандарту предприятия и подать.","—","—"))
   LazyColumn(verticalArrangement=Arrangement.spacedBy(10.dp)){
    item{Text(name,style=MaterialTheme.typography.headlineSmall);Text("Выход: $out г")}
    item{Text("Технологический процесс приготовления",style=MaterialTheme.typography.titleLarge)}
    items(steps.size){i->Card{Column(Modifier.padding(14.dp)){Text(steps[i].title,style=MaterialTheme.typography.titleMedium);Text(steps[i].text);Text("Режим: ${steps[i].mode} • Время: ${steps[i].time}")}}}
    item{Button({result=false},Modifier.fillMaxWidth()){Text("Изменить")}}
   }
  }
 }
}

class MainActivity:ComponentActivity(){
 override fun onCreate(b:Bundle?){super.onCreate(b);setContent{App()}}
}
