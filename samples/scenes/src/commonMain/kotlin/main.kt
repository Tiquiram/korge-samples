import com.soywiz.korge.Korge
import com.soywiz.korge.input.*
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.service.process.NativeProcess
import com.soywiz.korge.ui.UISkin
import com.soywiz.korge.ui.uiTextButton
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.lang.unsupported
import com.soywiz.korio.net.URL
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = MyModule))

object MyModule : Module() {
	override val mainScene: KClass<out Scene> = MyScene1::class

	override suspend fun AsyncInjector.configure() {
		mapInstance(MyDependency("HELLO WORLD"))
		mapPrototype { MyScene1(get()) }
		mapPrototype { MyScene2(get()) }
		mapPrototype { MyScene3(get()) }
		mapPrototype { MyScene4(get()) }
		mapPrototype { MyScene5(get()) }
	}
}

class MyDependency(val value: String)

class MyScene1(val myDependency: MyDependency) : Scene() {
	override suspend fun Container.sceneInit() {
		text("MyScene1: ${myDependency.value}") { smoothing = false }
		solidRect(root.width * 10, root.height * 10, Colors.DARKCYAN) {
			position(0, 0)
			alpha = 0.7
		}
		text("MyScene1: ${myDependency.value}") { smoothing = false }
		uiTextButton(500.0, 100.0) {
			text = "Log In / Create User"
			position(root.width * 0.3, root.height * 0.3)
			onClick {
				sceneContainer.changeTo<MyScene2>()
			}
			enable()
		}
	}


	class MyScene2(val myDependency: MyDependency) : Scene() {
	override suspend fun Container.sceneInit() {
		text("MyScene2: ${myDependency.value}") { smoothing = false }
		solidRect(root.width * 10, root.height * 10, Colors.BLUEVIOLET) {
			position(0, 0)
			onClick {
				sceneContainer.changeTo<MyScene1>(MyDependency("From MyScene2"))
			}
		}
		uiTextButton(500.0, 100.0) {
			text = "Log In"
			position(root.width * 0.3, root.height * 0.3)
			onClick {
				sceneContainer.changeTo<MyScene3>()
			}
			enable()
		}
	}
}


class MyScene3(val myDependency: MyDependency) : Scene() {
	override suspend fun Container.sceneInit() {
		text("MyScene1: ${myDependency.value}") { smoothing = false }
		solidRect(root.width * 10, root.height * 10, Colors.BLUE) {
			position(0, 0)
			alpha = 0.7
		}
		uiTextButton(500.0, 100.0) {
			text = "Log In"
			position(root.width * 0.3, root.height * 0.3)
			onClick {
				sceneContainer.changeTo<MyScene4>()
			}
			enable()
		}
		uiTextButton(500.0, 100.0) {
			text = "Back"
			position(root.width * 0.3, root.height * 0.5)
			onClick {
				sceneContainer.changeTo<MyScene2>()
			}
			enable()
		}
	}
}


class MyScene4(val myDependency: MyDependency) : Scene() {
	override suspend fun Container.sceneInit() {
		text("MyScene1: ${myDependency.value}") { smoothing = false }
		solidRect(root.width * 10, root.height * 10, Colors.PALEGREEN) {
			position(0, 0)
			alpha = 0.7
		}
		uiTextButton(500.0, 100.0) {
			text = "Game1"
			position(root.width * 0.3, root.height * 0.1)
			onClick {
				sceneContainer.changeTo<MyScene5>()
			}
			enable()
		}
		uiTextButton(500.0, 100.0) {
			text = "Game2"
			position(root.width * 0.3, root.height * 0.3)
			onClick {
				sceneContainer.changeTo<MyScene5>()
			}
			enable()
		}
		uiTextButton(500.0, 100.0) {
			text = "Game3"
			position(root.width * 0.3, root.height * 0.5)
			onClick {
				sceneContainer.changeTo<MyScene5>()
			}
			enable()
		}
		uiTextButton(500.0, 100.0) {
			text = "Back"
			position(root.width * 0.3, root.height * 0.7)
			onClick {
				sceneContainer.changeTo<MyScene3>()
			}
			enable()
		}
	}
}

class MyScene5(val myDependency: MyDependency) : Scene() {
	override suspend fun Container.sceneInit() {
		text("THIS IS SUPPOSED TO BE A GAME: ${myDependency.value}") { smoothing = false }
		solidRect(root.width * 10, root.height * 10, Colors.WHITE) {
			position(0, 0)
			alpha = 0.7
		}
		uiTextButton(500.0, 100.0) {
			text = "Exit"
			position(root.width * 0.3, root.height * 0.1)
			onClick {
				sceneContainer.changeTo<MyScene1>()
			}
			enable()
		}
	}
}
