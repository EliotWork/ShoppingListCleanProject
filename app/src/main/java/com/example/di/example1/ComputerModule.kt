package com.example.di.example1

import dagger.Module
import dagger.Provides

@Module
class ComputerModule {

    @Provides
    fun provideMonitor(): Monitor {
        return Monitor()
    }

    @Provides
    fun provideMemory(): Memory {
        return Memory()
    }

    @Provides
    fun provideProcessor(): Processor {
        return Processor()
    }

    @Provides
    fun provideStorage(): Storage {
        return Storage()
    }


    @Provides
    fun provideComputerTower(
        memory: Memory,
        processor: Processor,
        storage: Storage,
    ): ComputerTower {
        return ComputerTower(
            memory = memory,
            processor = processor,
            storage = storage
        )
    }

    @Provides
    fun provideMouse(): Mouse {
        return Mouse()
    }


    @Provides
    fun provideKeyboard(): Keyboard {
        return Keyboard()
    }


    @Provides
    fun provideComputer(
        monitor: Monitor,
        computerTower: ComputerTower,
        keyboard: Keyboard,
        mouse: Mouse
    ): Computer {
        return Computer(
            computerTower = computerTower,
            monitor = monitor,
            keyboard = keyboard,
            mouse = mouse
        )
    }
}